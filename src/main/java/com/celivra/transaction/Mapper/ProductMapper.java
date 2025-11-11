package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("insert into product(name, price, image, description, category, _condition, status, user_id) " +
            "values(#{name}, #{price}, #{image}, #{description}, #{category}, #{condition}, #{status}, #{userId})")
    Boolean addProduct(Product product);


    @Update("update product set name=#{name}, price=#{price}, image=#{image}, description=#{description}, " +
            "category=#{category}, _condition=#{condition}, status=#{status}, user_id=#{userId} where id=#{id}")
    Boolean updateProduct(Product product);

    @Select("select * from product where id=#{id}")
    Product getProductById(Integer id);

    @Select("select * from product where user_id = #{id} order by id desc")
    List<Product> getProductsByUserId(Integer id);

    @Select("select * from product order by id desc")
    List<Product> getAllProducts();
}
