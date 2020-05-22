package com.example.demo.sendmail.external.impl;

import com.example.demo.sendmail.domain.model.User;
import com.example.demo.sendmail.external.Secret;

public class SecretUserImpl implements Secret {
    /**
     * 外部APIからユーザ情報を呼び出す想定
     * 想定上のため、暫定固定値を返却 TODO べた書きでなく最低限Config取得位にはしたい
     */
    public User getUser(int userId) {
        User user = new User();

        user.setName("予約太郎");
        user.setMailAddress("yoyaku_tarou@example.com");

        return user;
    }
}
