package com.example.springdemo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo.entity.User;
import org.springframework.stereotype.Repository;

@Repository //代表这是持久层
public interface UserMapper extends BaseMapper<User> {
}
