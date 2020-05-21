package com.example.demo.sendmail.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.sendmail.domain.service.SendMailService;
import com.example.demo.sendmail.domain.service.impl.SendMailServiceCancelImpl;
import com.example.demo.sendmail.domain.service.impl.SendMailServiceCreateImpl;
import com.example.demo.sendmail.domain.service.impl.SendMailServiceUpdateImpl;

@RestController
public class SendMailController {

    //    @Autowired
    //    @Qualifier("service名")
    //    サービスクラス service;
    @Autowired
    SendMailServiceCreateImpl createService;

    @Autowired
    SendMailServiceUpdateImpl updateService;

    @Autowired
    SendMailServiceCancelImpl cancelService;

    /**
     * メール送信用処理
     */
    @GetMapping("/sendmail")
    public String sendMail(
            @RequestParam("type") String type,
            @RequestParam("id") Integer id) {
        // System.out.println("type:" + type + "id:" + id);

        try {
            SendMailService service = this.getService(type);
            service.sendMail(type, id);
        } catch (Exception e) {
            return "NG";
        }

        return "OK";
    }

    /**
     * リクエストパラメータに応じて、サービスインスタンスを分岐
     */
    private SendMailService getService(String type) throws Exception {
        switch (type) {
        case "CREATE":
            return createService;
        case "UPDATE":
            return updateService;
        case "CANCEL":
            return cancelService;
        default:
            throw new Exception();
        }
    }
}
