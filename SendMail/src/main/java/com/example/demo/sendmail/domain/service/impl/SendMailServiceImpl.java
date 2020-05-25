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

    /* データ取得処理用 */
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    Secret secret;

    /* メール送信処理用 */
    SpringTemplateEngine templateEngine;
    ClassLoaderTemplateResolver templateResolver;
    // テンプレート名
    private String templateName;

    @Override
    public boolean sendMail(Request request) {
        /* リクエスト種別毎の初期処理 */
        this.init(request);

        /* データ取得 */
        System.out.println("SendMailServiceImpl");
        Reservation reservation = reservationMapper.select(request.getReservationId());
        System.out.println(reservation);
        Shop shop = shopMapper.select(reservation.getShopId());
        System.out.println(shop);
        User user = secret.getUser(reservation.getUserId());
        System.out.println(user);

        Context context = new Context();
        String body = this.getHtmlMailBody(context);
        System.out.println(body);
        return true;
    }

    /**
     * リクエスト種別毎の初期処理
     */
    private void init(Request request) {
        // 初期化 コンストラクタでやらせるべき？要調査
        this.templateEngine = new SpringTemplateEngine();
        this.templateResolver = new ClassLoaderTemplateResolver();

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
     * HTMLメールのBody部を取得
     */
    private String getHtmlMailBody(Context context) {
        this.templateEngine.setTemplateResolver(this.mailTemplateResolver());
        return this.templateEngine.process(this.templateName, context); // メソッド構成変える必要ありそう

    }

    /**
     * メールテンプレート解決
     */
    private ClassLoaderTemplateResolver mailTemplateResolver() {
        this.templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateResolver.setPrefix("templates/mail/html/");
        this.templateResolver.setSuffix(".html");
        this.templateResolver.setCharacterEncoding("UTF-8");
        this.templateResolver.setCacheable(true);
        return templateResolver; // メソッド構成変える必要ありそう
    }
}
