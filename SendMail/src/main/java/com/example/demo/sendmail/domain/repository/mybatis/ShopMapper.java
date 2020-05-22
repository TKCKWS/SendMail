package com.example.demo.sendmail.domain.repository.mybatis;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.sendmail.domain.model.Shop;

@Mapper
public interface ShopMapper {

    // Shopテーブルのデータを取得
    public Shop select(String id);
}
