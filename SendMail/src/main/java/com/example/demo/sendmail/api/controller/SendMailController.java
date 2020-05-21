package com.example.demo.sendmail.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMailController {

    //    @Autowired
    //    @Qualifier("service名")
    //    サービスクラス service;

    /**
     * メール送信用処理
     */
    @GetMapping("/sendmail")
    public String sendMail(
            @RequestParam("type") String type,
            @RequestParam("id") String id) {

        // System.out.println("type:" + type + "id:" + id);
        return "OK";
    }
}
