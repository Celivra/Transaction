package com.celivra.transaction.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransRecord {
    Integer id;
    Integer userId;
    Integer productId;
    LocalDateTime purchaseTime;
    String status;
    String description;

    public TransRecord() {}


    public TransRecord(Integer userId, Integer productId, LocalDateTime purchaseTime, String status, String description) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
    }

    public TransRecord(Integer id, Integer userId, Integer productId, LocalDateTime purchaseTime, String status, String description) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
    }
}
