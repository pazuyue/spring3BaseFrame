package com.example.springdemo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity  implements Serializable {

    private static final long serialVersionUID = 5237730257103305078L;

    private Long id;
    private String userName;
    private Character userSex;
    private String headers;
}
