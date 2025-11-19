package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.TransRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransRecordMapper {

    @Insert("insert into t_record(buyer_id, seller_id, product_id, purchase_time, status, description, send_address, receive_address) " +
            "values(#{buyerId}, #{sellerId}, #{productId}, #{purchaseTime},#{status}, #{description}, #{sendAddress}, #{receiveAddress})")
    Boolean addTransRecord(TransRecord transRecord);

    @Insert("update t_record set buyer_id=#{buyerId}, seller_id=#{sellerId}, product_id=#{productId}, " +
            "purchase_time=#{purchaseTime}, status=#{status}, " +
            "description=#{description}, send_address=#{sendAddress}, " +
            "receive_address=#{receiveAddress} where id=#{id}")
    Boolean updateTransRecord(TransRecord transRecord);

    @Delete("delete from t_record where id = #{id}")
    Boolean removeTransRecord(TransRecord transRecord);

    @Select("select * from t_record where id = #{id}")
    TransRecord getTransRecordById(Integer id);

    @Select("select * from t_record where seller_id=#{sellerId} order by id desc")
    List<TransRecord> getTransRecordsBySeller(Integer userId);

    @Select("select * from t_record where buyer_id=#{buyerId} order by id desc")
    List<TransRecord> getTransRecordsByBuyer(Integer userId);
}
