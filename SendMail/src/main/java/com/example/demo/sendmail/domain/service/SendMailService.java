package com.example.demo.sendmail.domain.service;

import com.example.demo.domain.model.Request;

public interface SendMailService {

    // メール送信
    public boolean sendMail(Request request);

}
