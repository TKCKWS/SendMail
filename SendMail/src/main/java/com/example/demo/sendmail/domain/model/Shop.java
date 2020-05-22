package com.example.demo.sendmail.domain.model;

import lombok.Data;

@Data
public class Shop {
    int shopId; // お店ID
    String name; // お店名
    String mailAddress; // お店メールアドレス
}
