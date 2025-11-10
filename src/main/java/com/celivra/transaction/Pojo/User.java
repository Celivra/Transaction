package com.celivra.transaction.Pojo;

import lombok.Data;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String phone;
    String email;

    public User(){}
    public User(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public User(Integer id, String username, String password, String phone, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
