package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.UserMapper;
import com.celivra.transaction.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Integer addUser(User user) {
        User checkUser = userMapper.getUserByUsername(user.getUsername());
        if (checkUser != null) {
            return -1;
        }

        if(userMapper.addUser(user)){
            return 1;
        }else{
            return 0;
        }
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
