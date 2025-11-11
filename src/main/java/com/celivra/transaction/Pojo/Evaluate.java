package com.celivra.transaction.Pojo;

import lombok.Data;

@Data
public class Evaluate {
    Integer id;
    Integer userId;
    Integer productId;
    String username;
    String content;

    public Evaluate() {}

    public Evaluate(Integer userId, Integer productId, String username, String content) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.username = username;
    }

    public Evaluate(Integer id, Integer userId, Integer productId, String username, String content) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
        this.username = username;
    }
}
