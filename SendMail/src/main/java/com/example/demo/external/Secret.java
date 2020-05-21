package com.example.demo.external;

import com.example.demo.domain.model.User;

public interface Secret {
    public User getUser(int userId);
}
