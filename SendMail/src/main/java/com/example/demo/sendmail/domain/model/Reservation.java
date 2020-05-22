package com.example.demo.sendmail.domain.model;

import java.text.SimpleDateFormat;

import lombok.Data;

@Data
public class Reservation {
    private int reservationId; // 予約ID
    private int userId; // ユーザID
    private int shopId; // お店ID
    private SimpleDateFormat reservationStart; // 予約開始日時
    private SimpleDateFormat reservationEnd; // 予約終了日時
    private int number; // 予約人数
    private int status; // ステータス
}
