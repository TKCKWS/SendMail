package com.example.demo.sendmail.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Transactional
@Service("SendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    Secret secret;

    @Override
    public boolean sendMail(Request request) {
        /* データ取得 */
        System.out.println("SendMailServiceImpl");
        Reservation reservation = reservationMapper.select(request.getReservationId());
        System.out.println(reservation);
        Shop shop = shopMapper.select(reservation.getShopId());
        System.out.println(shop);
        User user = secret.getUser(reservation.getUserId());
        System.out.println(user);

        Context context = new Context();
        String body = this.getHtmlMailBody("create", context);
        System.out.println(body);
        return true;
    }

    /**
     * HTMLメールのBody部を取得
     */
    private String getHtmlMailBody(String templateName, Context context) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(this.mailTemplateResolver());
        return templateEngine.process(templateName, context);

    }

    /**
     * メールテンプレート解決
     */
    private ClassLoaderTemplateResolver mailTemplateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("templates/mail/html/layout/user/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(true);
        return templateResolver;
    }
}
