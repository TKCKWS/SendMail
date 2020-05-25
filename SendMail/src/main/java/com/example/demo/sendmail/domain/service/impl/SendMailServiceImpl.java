package com.example.demo.sendmail.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import com.example.demo.sendmail.domain.model.Request;
import com.example.demo.sendmail.domain.model.Reservation;
import com.example.demo.sendmail.domain.model.Shop;
import com.example.demo.sendmail.domain.model.User;
import com.example.demo.sendmail.domain.repository.mybatis.ReservationMapper;
import com.example.demo.sendmail.domain.repository.mybatis.ShopMapper;
import com.example.demo.sendmail.domain.service.SendMailService;
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
    // メールテンプレート定義
    private static final String MAIL_TEMPLATE_PATH_ROOT = "templates/mail/";
    // メールテンプレートHTML用
    private static final String MAIL_TEMPLATE_HTML = "html";
    // メール文字コード
    private static final String MAIL_CHARACTER_CODE= "UTF-8";

    @Override
    public boolean sendMail(Request request) {
        // リクエスト種別毎の初期処理
        this.init(request);

        // データ取得
        this.getData();

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

        switch (request.getType()) { // スマートな方法ありそうだが、とりあえずswitchで分岐
        case "USER_NEW": // ユーザ向け新規
            this.templateName = "layout/user/new";
            break;
        case "USER_UPDATE": // ユーザ向け更新
            this.templateName = "layout/user/update";
            break;
        case "USER_CANCEL": // ユーザ向けキャンセル
            this.templateName = "layout/user/cancel";
            break;
        case "SHOP_NEW": // お店向け新規
            this.templateName = "layout/shop/new";
            break;
        case "SHOP_UPDATE": // お店向け更新
            this.templateName = "layout/shop/update";
            break;
        case "SHOP_CANCEL": // お店向けキャンセル
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
    private void getData() {
        // 予約情報取得
        this.reservation = reservationMapper.select(request.getReservationId());
        System.out.println(this.reservation);
        // お店情報取得
        this.shop = shopMapper.select(reservation.getShopId());
        System.out.println(this.shop);
        // ユーザ情報取得
        user = secret.getUser(this.reservation.getUserId());
        System.out.println(this.user);

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
            String prefix = SendMailServiceImpl.MAIL_TEMPLATE_PATH_ROOT + SendMailServiceImpl.MAIL_TEMPLATE_HTML + '/';
            this.templateResolver.setPrefix(prefix);
            this.templateResolver.setSuffix("." + SendMailServiceImpl.MAIL_TEMPLATE_HTML);
        }
        this.templateResolver.setCharacterEncoding(SendMailServiceImpl.MAIL_CHARACTER_CODE);
        this.templateResolver.setCacheable(true);
        return templateResolver;
    }

    /**
     * HTMLメール送信判定
     */
    private boolean isHtmlMail() {
        return true; // テキストメール分岐を作る場合用の準備
    }
}
