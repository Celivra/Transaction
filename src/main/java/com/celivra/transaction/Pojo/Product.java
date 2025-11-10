package com.celivra.transaction.Pojo;

import lombok.Data;

@Data
public class Product {
    Integer id;
    String name;
    Double price;
    String image;
    String category;
    String condition;
    String status;
    String description;

    public Product() {}

    public Product(String name, Double price, String image, String category, String condition, String status, String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.condition = condition;
        this.status = status;
        this.description = description;
    }

    public Product(Integer id, String name, Double price, String image, String category, String condition, String status, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
        this.condition = condition;
        this.status = status;
        this.description = description;
    }
}
