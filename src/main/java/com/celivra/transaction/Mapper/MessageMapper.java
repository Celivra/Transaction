package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {

    @Select("SELECT * FROM message WHERE " +
            "((sender_id=#{user1} AND receiver_id=#{user2}) OR " +
            "(sender_id=#{user2} AND receiver_id=#{user1})) AND product_id=#{productId} " +
            "ORDER BY send_time ASC")
    List<Message> getChatHistory(Integer user1, Integer user2, Integer productId);

    @Insert("INSERT INTO message(sender_id, receiver_id, product_id, content, send_time) " +
            "VALUES(#{senderId}, #{receiverId}, #{productId}, #{content}, #{sendTime})")
    boolean insertMessage(Message msg);
}
