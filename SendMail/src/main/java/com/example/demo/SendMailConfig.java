package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
/**
 * application.ymlからsendmail用のConfig値を読みだすファイル
 */
public class SendMailConfig {
    // ユーザ予約FQDN
    @Value("${sendmail.url.fqdn.user.reserve}")
    private String userReserveFQDN;
    // お店予約FQDN
    @Value("${sendmail.url.fqdn.user.reserve}")
    private String shopReserveFQDN;
    // 送信元メールアドレス
    @Value("$spring.mail.username")
    private String sourceMailAddress;
}
