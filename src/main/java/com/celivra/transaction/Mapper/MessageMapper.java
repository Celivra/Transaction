package com.celivra.transaction.Mapper;

import com.celivra.transaction.DTO.ChatSummary;
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
    // 获取和某用户相关的所有聊天摘要
    @Select("""
        SELECT 
            m.product_id AS productId,
            CASE 
                WHEN m.sender_id = #{userId} THEN m.receiver_id
                ELSE m.sender_id
            END AS otherUserId,
            u.username AS otherUsername,
            p.name AS productName,
            m.content AS lastMessage,
            m.send_time AS lastTime
        FROM message m
        JOIN product p ON m.product_id = p.id
        JOIN user u 
            ON u.id = CASE 
                        WHEN m.sender_id = #{userId} THEN m.receiver_id
                        ELSE m.sender_id
                      END
        WHERE m.id IN (
            SELECT id FROM (
                SELECT 
                    id,
                    ROW_NUMBER() OVER (
                        PARTITION BY product_id,
                        LEAST(sender_id, receiver_id),
                        GREATEST(sender_id, receiver_id)
                        ORDER BY send_time DESC
                    ) AS rn
                FROM message
                WHERE sender_id = #{userId} OR receiver_id = #{userId}
            ) t WHERE rn = 1
        )
        ORDER BY lastTime DESC
    """)
    List<ChatSummary> getChatSummaryList(Integer userId);
}
