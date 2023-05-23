package com.example.springdemo.Servlet;

import com.example.springdemo.Mapper.UserMapper;
import com.example.springdemo.Pojo.User;
import com.example.springdemo.Servlet.IService.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"userCache"})
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable("userList") // 标志读取缓存操作，如果缓存不存在，则调用目标方法，并将结果放入缓存
    public List<User> getAll() {
        List<User> users = userMapper.selectList(null);
        return users;
    }

    @Override
    @Cacheable(cacheNames = { "user" }, key = "#id")//如果缓存存在，直接读取缓存值；如果缓存不存在，则调用目标方法，并将结果放入缓存
    public User getOne(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public void insertUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void deleteAll1() {

    }

    @Override
    public void deleteAll12() {

    }
}
