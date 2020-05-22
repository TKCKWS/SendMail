package com.example.demo.sendmail.domain.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.sendmail.domain.model.Request;

@Transactional
@Service
public class SendMailServiceCancelImpl extends SendMailServiceImpl {

    @Override
    public boolean sendMail(Request request) {
        System.out.println("SendMailServiceCancelImpl");
        return true;
    }
}
