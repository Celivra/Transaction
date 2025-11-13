package com.celivra.transaction.Pojo;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class TransRecord {
    Integer id;
    Integer buyerId;
    Integer sellerId;
    Integer productId;
    LocalDateTime purchaseTime;
    String status;
    String description;
    String sendAddress;
    String receiveAddress;

    public TransRecord() {}

    public TransRecord(Integer id, Integer buyerId, Integer sellerId, Integer productId, LocalDateTime purchaseTime, String status, String description, String sendAddress, String receiveAddress) {
        this.id = id;
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
    }

    public TransRecord(Integer buyerId, Integer sellerId, Integer productId, LocalDateTime purchaseTime, String status, String description, String sendAddress, String receiveAddress) {
        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.purchaseTime = purchaseTime;
        this.status = status;
        this.description = description;
        this.sendAddress = sendAddress;
        this.receiveAddress = receiveAddress;
    }
    public String FormatDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return purchaseTime.format(dtf);
    }
}
