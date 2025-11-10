package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.TransRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TransRecordMapper {

    @Insert("insert into t_record(user_id, product_id, purchase_time, status, description) " +
            "values({userId}, #{productId}, #{purchaseTime},#{status}, #{description})")
    Boolean addTransRecord(TransRecord transRecord);

    @Insert("update t_record set user_id=#{userId}, product_id=#{productID}, " +
            "purchase_time=#{purchaseTime}, status=#{status}, " +
            "description=#{description} where id=#{id}")
    Boolean updateTransRecord(TransRecord transRecord);

    @Select("select * from t_record where user_id=#{userId} order by id desc")
    List<TransRecord> getTransRecordsByUserId(Integer userId);
}
