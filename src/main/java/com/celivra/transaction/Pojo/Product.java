package com.celivra.transaction.Pojo;

import lombok.Data;

@Data
public class Product {
    Integer id;
    Integer userId;
    String name;
    Double price;
    String image;
    String category;
    String condition;
    String status;
    String description;

    public Product() {}

    public Product(String name, Double price, String image, String category, String condition, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.condition = condition;
        this.description = description;
    }

    public Product(Integer userId, String name, Double price, String image, String category, String condition, String status, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.condition = condition;
        this.status = status;
        this.description = description;
        this.userId = userId;
    }

    public Product(Integer id, Integer userId, String name, Double price, String image, String category, String condition, String status, String description) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.condition = condition;
        this.status = status;
        this.description = description;
    }
}
