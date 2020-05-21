package com.example.demo.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class Request {
    private String type; // リクエスト種別
    private int reservationId; // 予約ID
    private Date start; // 予約開始日時
    private Date end; // 予約終了日時
    private int number; // 予約人数
}
