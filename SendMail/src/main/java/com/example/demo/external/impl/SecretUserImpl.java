package com.example.demo.external.impl;

import com.example.demo.domain.model.User;
import com.example.demo.external.Secret;

public class SecretUserImpl implements Secret {
    /**
     * 外部APIからユーザ情報を呼び出す想定
     * 想定上のため、暫定固定値を返却
     */
    public User getUser(int userId) {
        User user = new User();

        user.setName("予約太郎");
        user.setMailAddress("yoyaku_tarou@example.com");

        return user;
    }
}
