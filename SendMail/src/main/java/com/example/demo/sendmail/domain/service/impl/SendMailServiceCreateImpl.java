package com.example.demo.sendmail.domain.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.sendmail.domain.service.SendMailService;

@Transactional
@Service
public class SendMailServiceCreateImpl implements SendMailService {

    //@Autowired
    //Mapper

    @Override
    public boolean sendMail(String type, Integer id) {
        System.out.println("SendMailServiceCreateImpl");
        return true;
    }
}
