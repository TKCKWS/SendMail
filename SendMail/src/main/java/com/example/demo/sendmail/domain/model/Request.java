package com.example.demo.sendmail.domain.model;

import lombok.Data;

@Data
public class Request {
    private String type; // リクエスト種別
    private int reservationId; // 予約ID
}
