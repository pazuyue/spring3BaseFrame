package com.example.springdemo.Servlet.User;

import com.example.springdemo.Mapper.UserMapper;
import com.example.springdemo.Pojo.User;
import com.example.springdemo.Servlet.User.IService.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<User> getUserList(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> list=userMapper.selectList(null);
        PageInfo<User> pageData= new PageInfo<User>(list);
        System.out.println("当前页："+pageData.getPageNum());
        System.out.println("页面大小："+pageData.getPageSize());
        System.out.println("总数："+pageData.getTotal());
        System.out.println("总页数："+pageData.getPages());
        return pageData;
    }

    @Override
    @Cacheable(cacheNames = { "user" }, key = "#id")//如果缓存存在，直接读取缓存值；如果缓存不存在，则调用目标方法，并将结果放入缓存
    public User getOne(Integer id) {
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
    public void deleteAll() {

    }
}
