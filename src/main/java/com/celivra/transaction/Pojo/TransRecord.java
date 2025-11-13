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
    String sendAddress;
    String receiveAddress;

    public TransRecord() {}


    public TransRecord(Integer id, Integer userId, Integer productId, LocalDateTime purchaseTime, String status, String description, String sendAddress, String receiveAddress) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
    }

    public TransRecord(Integer userId, Integer productId, LocalDateTime purchaseTime, String status, String description, String sendAddress, String receiveAddress) {
        this.userId = userId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
    }
}
