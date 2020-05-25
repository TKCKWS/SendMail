package com.example.demo.sendmail.domain.model;

import lombok.Data;

@Data
public class User {
    String mailAddress; // メールアドレス
    String name; // 名前
    String telephoneNumber; // 電話番号
}
