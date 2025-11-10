package com.celivra.transaction.Service;

import com.celivra.transaction.Mapper.UserMapper;
import com.celivra.transaction.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Boolean addUser(User user) {
        return userMapper.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }
}
