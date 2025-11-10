package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.Evaluate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluateMapper {
    @Insert("insert into evaluate(user_id, product_id, content) " +
            "values(#{userId}, #{productId}, #{content})")
    Boolean addEvaluate(Evaluate e);

    @Select("select * from evaluate where product_id=#{productId} order by id desc")
    List<Evaluate> getEvaluatesByProductId(Integer productId);
}
