package com.example.demo.sendmail.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.sendmail.domain.model.Request;
import com.example.demo.sendmail.domain.service.impl.SendMailServiceImpl;

@RestController
public class SendMailController {

    @Autowired
    @Qualifier("SendMailServiceImpl")
    SendMailServiceImpl service;
// 不要?サービス実装検討後削除
//    @Autowired
//    SendMailServiceCreateImpl createService;
//
//    @Autowired
//    SendMailServiceUpdateImpl updateService;
//
//    @Autowired
//    SendMailServiceCancelImpl cancelService;

    /**
     * メール送信用処理
     */
    @GetMapping("/sendmail")
    public String sendMail(@ModelAttribute Request request) {
        System.out.println(request);

        try {
// 不要?サービス実装検討後削除
//            SendMailService service = this.getService(request.getType());
            service.sendMail(request);
        } catch (Exception e) {
            return "NG";
        }

        return "OK";
    }

 // 不要?サービス実装検討後削除
    /**
     * リクエストパラメータに応じて、サービスインスタンスを分岐
     */
//    private SendMailService getService(String type) throws Exception {
//        switch (type) {
//        case "CREATE":
//            return createService;
//        case "UPDATE":
//            return updateService;
//        case "CANCEL":
//            return cancelService;
//        default:
//            throw new Exception();
//        }
//    }
}
