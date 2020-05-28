package com.example.demo.sendmail.domain.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.demo.SendMailConfig;
import com.example.demo.sendmail.constants.RequestType;
import com.example.demo.sendmail.constants.constants;
import com.example.demo.sendmail.domain.model.Request;
import com.example.demo.sendmail.domain.model.Reservation;
import com.example.demo.sendmail.domain.model.Shop;
import com.example.demo.sendmail.domain.model.User;
import com.example.demo.sendmail.domain.repository.mybatis.ReservationMapper;
import com.example.demo.sendmail.domain.repository.mybatis.ShopMapper;
import com.example.demo.sendmail.domain.service.SendMailService;
import com.example.demo.sendmail.exception.ReservationNotFoundException;
import com.example.demo.sendmail.external.Secret;

@Service("SendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService {

    /* リクエスト */
    protected Request request;

    /* データ取得処理用 */
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    Secret secret;
    @Autowired
    SendMailConfig config;

    // 予約情報
    Reservation reservation;
    // お店情報
    Shop shop;
    // ユーザ情報
    User user;

    /* メール送信処理用 */
    SpringTemplateEngine templateEngine;
    ClassLoaderTemplateResolver templateResolver;
    Context context;
    // テンプレート名
    private String templateName;
    // メールボディ部
    private String mailBody;

    @Override
    public boolean sendMail(Request request) throws Exception{
        // リクエスト種別毎の初期処理
        this.init(request);

        // データ取得
        this.getData();

        // 出力内容設定
        this.setContext();

        // ボディ部取得
        this.getMailBody();

        // メール送信
        //this.send();

        return true;
    }

    /**
     * リクエスト種別毎の初期処理
     */
    private void init(Request request) {
        // 初期化 コンストラクタでやらせるべき？Autowiredされたサービスをどう初期化すべきか要調査
        this.request = request;
        this.templateEngine = new SpringTemplateEngine();
        this.templateResolver = new ClassLoaderTemplateResolver();
        this.context = new Context();

        switch (RequestType.valueOf(request.getType())) { // スマートな方法ありそうだが、とりあえずswitchで分岐
        case USER_NEW: // ユーザ向け新規
            this.templateName = "layout/user/new";
            break;
        case USER_UPDATE: // ユーザ向け更新
            this.templateName = "layout/user/update";
            break;
        case USER_CANCEL: // ユーザ向けキャンセル
            this.templateName = "layout/user/cancel";
            break;
        case SHOP_NEW: // お店向け新規
            this.templateName = "layout/shop/new";
            break;
        case SHOP_UPDATE: // お店向け更新
            this.templateName = "layout/shop/update";
            break;
        case SHOP_CANCEL: // お店向けキャンセル
            this.templateName = "layout/shop/cancel";
            break;
        default:
            // バリデーションを作った後は入らないはず Exceptionをthrow?
            break;
        }
    }

    /**
     * データ取得
     */
    private void getData() throws Exception {

        // 予約情報取得
        this.reservation = reservationMapper.select(request.getReservationId());
        if (this.reservation == null) {
            throw new ReservationNotFoundException();
        }
        System.out.println(this.reservation);
        // お店情報取得
        this.shop = shopMapper.select(reservation.getShopId());
        System.out.println(this.shop);
        // ユーザ情報取得
        user = secret.getUser(this.reservation.getUserId());
        System.out.println(this.user);
    }

    /**
     * 出力内容設定
     */
    private void setContext() {

        /* 予約情報 */
        // 予約ID
        this.context.setVariable("reservationId", this.reservation.getReservationId());
        // 予約日
        this.context.setVariable("reservationDate",
                this.convertLocalDateTimeToString(this.reservation.getReservationStart(),
                        constants.DATE_FORMAT_YMD));
        // 予約開始時間
        this.context.setVariable("reservationStart",
                this.convertLocalDateTimeToString(this.reservation.getReservationStart(),
                        constants.TIME_FORMAT_HM));
        // 予約終了時間
        this.context.setVariable("reservationEnd",
                this.convertLocalDateTimeToString(this.reservation.getReservationEnd(),
                        constants.TIME_FORMAT_HM));
        // 予約人数
        this.context.setVariable("reservationNumber", this.reservation.getNumber());
        // キャンセルメール判定
        this.context.setVariable("isCancel", RequestType.isCancel(this.request.getType()));

        /* お店情報 */
        this.context.setVariable("shopName", this.shop.getName());

        /* ユーザ情報 */
        // ユーザ名
        this.context.setVariable("userName", this.user.getName());
        // 電話番号
        this.context.setVariable("userTelephoneNumber", this.user.getTelephoneNumber());

        /* その他 */
        // ユーザ予約URL(FQDN部分は環境により分岐させるため、Configから取得)
        String userReserveUrl = config.getUserReserveFQDN() + constants.USER_RESERVE_URL;
        userReserveUrl += "?" + reservation.getReservationId(); //パラメータ追加
        this.context.setVariable("userReserveUrl", userReserveUrl);

        // ユーザ予約URL(FQDN部分は環境により分岐させるため、Configから取得)
        String shopReserveUrl = config.getUserReserveFQDN() + constants.SHOP_RESERVE_URL;
        shopReserveUrl += "?" + reservation.getReservationId(); //パラメータ追加
        this.context.setVariable("shopReserveUrl", shopReserveUrl);
    }

    /**
     * 送信するメールBody部を取得
     */
    private void getMailBody() {
        this.templateEngine.setTemplateResolver(this.getMailTemplateResolver());
        this.mailBody = templateEngine.process(this.templateName, this.context);
        System.out.println(this.mailBody);
    }

    /**
     * メールテンプレートリゾルバ設定
     */
    private ClassLoaderTemplateResolver getMailTemplateResolver() {
        // テキストメール分岐を作る場合に備えて分岐してリゾルバ設定
        if (isHtmlMail()) {
            this.templateResolver.setTemplateMode(TemplateMode.HTML);
            String prefix = constants.MAIL_TEMPLATE_PATH_ROOT + constants.MAIL_TEMPLATE_HTML + '/';
            this.templateResolver.setPrefix(prefix);
            this.templateResolver.setSuffix("." + constants.MAIL_TEMPLATE_HTML);
        }
        this.templateResolver.setCharacterEncoding(constants.MAIL_CHARACTER_CODE);
        this.templateResolver.setCacheable(true);
        return templateResolver;
    }

    /**
     * HTMLメール送信判定
     */
    private boolean isHtmlMail() {
        return true; // テキストメール分岐を作る場合用の準備
    }

    /**
     * 日時文字列変換
     */
    private String convertLocalDateTimeToString(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }

}
