package com.example.springdemo.Entity;

import lombok.Data;

@Data
public class UserEntity {
    private Long id;
    private String userName;
    private Character userSex;
    private String headers;
}
