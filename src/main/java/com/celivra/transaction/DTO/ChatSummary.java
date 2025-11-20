package com.celivra.transaction.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatSummary {
    private Integer productId;
    private Integer otherUserId;
    private String otherUsername;
    private String productName;
    private String lastMessage;
    private LocalDateTime lastTime;
}
