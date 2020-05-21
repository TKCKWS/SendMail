package com.example.demo.sendmail.domain.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.domain.model.Reservation;

@Mapper
public interface ReservationMapper {

    // Reservationテーブルのデータを取得
    public Reservation select(String id);

    // Reservationテーブルのデータを更新
    public int update(Reservation reservation);
}
