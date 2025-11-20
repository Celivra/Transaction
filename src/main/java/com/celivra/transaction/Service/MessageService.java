package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.MessageMapper;
import com.celivra.transaction.Pojo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper mapper;

    public List<Message> getChatHistory(Integer user1, Integer user2, Integer productId){
        return mapper.getChatHistory(user1, user2, productId);
    }

    public boolean sendMessage(Message msg){
        return mapper.insertMessage(msg);
    }
}
