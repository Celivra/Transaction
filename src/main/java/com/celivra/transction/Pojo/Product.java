package com.celivra.transction.Pojo;

import lombok.Data;

@Data
public class Product {
    Integer Id;
    String Name;
    Double Price;
    String Image;
    String Category;
    String Condition;
    String Status;
    String Description;

    public Product() {}

    public Product(String name, Double price, String image, String category, String condition, String status, String description) {
        Name = name;
        Price = price;
        Image = image;
        Category = category;
        Condition = condition;
        Status = status;
        Description = description;
    }

    public Product(Integer id, String name, Double price, String image, String category, String condition, String status, String description) {
        Id = id;
        Name = name;
        Price = price;
        Image = image;
        Category = category;
        Condition = condition;
        Status = status;
        Description = description;
    }
}
