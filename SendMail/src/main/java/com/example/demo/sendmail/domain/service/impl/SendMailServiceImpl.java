package com.example.demo.sendmail.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.sendmail.domain.model.Request;
import com.example.demo.sendmail.domain.model.Reservation;
import com.example.demo.sendmail.domain.model.Shop;
import com.example.demo.sendmail.domain.repository.mybatis.ReservationMapper;
import com.example.demo.sendmail.domain.repository.mybatis.ShopMapper;
import com.example.demo.sendmail.domain.service.SendMailService;

@Transactional
@Service("SendMailServiceImpl")
public class SendMailServiceImpl implements SendMailService {

    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ShopMapper shopMapper;
//    @Autowired
//    Secret secret;

    @Override
    public boolean sendMail(Request request) {
        System.out.println("SendMailServiceImpl");
        Reservation reservation = reservationMapper.select(request.getReservationId());
        System.out.println(reservation);
        Shop shop = shopMapper.select(reservation.getShopId());
        System.out.println(shop);
//        User user = secret.getUser(reservation.getUserId());
//        System.out.println(user);
        return true;
    }
}
