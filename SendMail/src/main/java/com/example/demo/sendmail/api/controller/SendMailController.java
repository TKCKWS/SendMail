package com.example.demo.sendmail.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.Errors;
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
     * @throws Exception
     */
    @GetMapping("/sendmail")
    public String sendMail(@ModelAttribute @Validated Request request, Errors errors) throws Exception {
        System.out.println(request);
        // バリデーションエラーが渡されたら、IllegalArgumentExceptionをthrow
        if(errors.hasErrors()) {
            System.out.println(errors);
            throw new IllegalArgumentException();
        }

        try {
            service.sendMail(request);
        } catch (Exception e) {
            // Controllerでログ出力して、ControllerAdviceで例外をhandleのイメージ
            System.out.println(e);
            throw e;
        }

        return "OK";
    }
}
