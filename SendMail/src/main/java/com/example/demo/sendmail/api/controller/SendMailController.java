package com.example.demo.sendmail.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
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

    /**
     * メール送信用処理
     */
    @GetMapping("/sendmail")
    public String sendMail(@ModelAttribute @Validated Request request) {
        System.out.println(request);

        try {
            System.out.println("service");
            service.sendMail(request);
        } catch (Exception e) {
            System.out.println(e);
            return "NG";
        }

        return "OK";
    }
}
