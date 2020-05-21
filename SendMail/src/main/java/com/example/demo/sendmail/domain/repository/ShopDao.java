package com.example.demo.sendmail.domain.repository;

import org.springframework.dao.DataAccessException;

import com.example.demo.domain.model.Shop;


public interface ShopDao {

    // Shopテーブルのデータを取得
    public Shop select(String id) throws DataAccessException;
}
