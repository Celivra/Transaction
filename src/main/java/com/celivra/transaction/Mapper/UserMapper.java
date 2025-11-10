package com.celivra.transaction.Mapper;

import com.celivra.transaction.Pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(username, password, phone, email) " +
            "values(#{username}, #{password}, #{phone}, #{email})")
    Boolean addUser(User user);

    @Select("select * from user where username=#{username}")
    User getUserByUsername(String username);

    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);
}
