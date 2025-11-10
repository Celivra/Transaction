package com.celivra.transction.Pojo;

import lombok.Data;

@Data
public class User {
    Integer Id;
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
        this.Id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
