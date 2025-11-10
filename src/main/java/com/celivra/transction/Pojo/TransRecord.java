package com.celivra.transction.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransRecord {
    Integer Id;
    Integer UserId;
    Integer ProductId;
    LocalDateTime PurchaseTime;
    String Status;

    public TransRecord() { }

    public TransRecord(Integer userId, Integer productId, LocalDateTime purchaseTime, String status) {
        UserId = userId;
        ProductId = productId;
        PurchaseTime = purchaseTime;
        Status = status;
    }

    public TransRecord(Integer id, Integer userId, Integer productId, LocalDateTime purchaseTime, String status) {
        this.Id = id;
        UserId = userId;
        ProductId = productId;
        PurchaseTime = purchaseTime;
        Status = status;
    }
}
