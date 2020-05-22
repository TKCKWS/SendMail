package com.example.demo.sendmail.external;

import com.example.demo.sendmail.domain.model.User;

public interface Secret {
    public User getUser(int userId);
}
