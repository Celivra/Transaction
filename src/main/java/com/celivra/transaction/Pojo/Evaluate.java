package com.celivra.transaction.Pojo;

import lombok.Data;

@Data
public class Evaluate {
    Integer Id;
    Integer userId;
    Integer productId;
    String content;

    public Evaluate() {}

    public Evaluate(Integer userId, Integer productId, String content) {
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }

    public Evaluate(Integer id, Integer userId, Integer productId, String content) {
        Id = id;
        this.userId = userId;
        this.productId = productId;
        this.content = content;
    }
}
