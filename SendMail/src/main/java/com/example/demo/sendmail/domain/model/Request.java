package com.example.demo.sendmail.domain.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.demo.sendmail.domain.model.validate.SendMailType;

import lombok.Data;

@Data
public class Request {
    @NotBlank
    @SendMailType // メール送信種別カスタムバリデーション
    private String type; // リクエスト種別

    @NotNull
    @Min(1)
    private int reservationId; // 予約ID
}
