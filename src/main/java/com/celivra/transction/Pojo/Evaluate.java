package com.celivra.transction.Pojo;

import lombok.Data;

@Data
public class Evaluate {
    Integer Id;
    Integer UserId;
    Integer ProductId;
    String Content;

    public Evaluate() {}

    public Evaluate(Integer userId, Integer productId, String content) {
        UserId = userId;
        ProductId = productId;
        Content = content;
    }

    public Evaluate(Integer id, Integer userId, Integer productId, String content) {
        Id = id;
        UserId = userId;
        ProductId = productId;
        Content = content;
    }
}
