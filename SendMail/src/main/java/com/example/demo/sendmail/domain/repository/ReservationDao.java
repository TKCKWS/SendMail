package com.example.demo.sendmail.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.Reservation;


public interface ReservationDao {

    // Reservationテーブルのデータを1件取得
    public Reservation select(String id) throws DataAccessException;

    // Reservationテーブルのデータを更新
    public int update(Reservation reservation) throws DataAccessException;
}
