package com.celivra.transaction.Pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    Integer id;
    Integer senderId;
    Integer receiverId;
    Integer productId;
    String content;
    LocalDateTime sendTime;
}
