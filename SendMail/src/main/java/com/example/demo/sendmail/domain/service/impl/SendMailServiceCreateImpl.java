package com.example.demo.sendmail.domain.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SendMailServiceCreateImpl extends SendMailServiceImpl {

    @Override
    public boolean sendMail(String type, Integer id) {
        System.out.println("SendMailServiceCreateImpl");
        return true;
    }
}
