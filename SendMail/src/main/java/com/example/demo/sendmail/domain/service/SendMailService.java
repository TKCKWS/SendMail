package com.example.demo.sendmail.domain.service;

public interface SendMailService {

    // メール送信
    public boolean sendMail(String type, Integer id);

}
