package com.example.demo.sendmail.external;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.sendmail.external.impl.SecretUserImpl;

@Configuration
public class ExternalConfig {
    @Bean
    public Secret getSecret() {
        Secret secret = new SecretUserImpl();
        return secret;
    }
}
