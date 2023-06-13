package com.example.springdemo.Service.Impl.User;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springdemo.Entity.User.User;
import com.example.springdemo.Mapper.User.UserMapper;
import com.example.springdemo.Service.User.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Transactional
    public Integer insert(User user)
    {
        this.baseMapper.insert(user);
        return 1/0;
    }

    @DS("#dataBaseName")//使用spel从参数获取
    public List<User> list(String dataBaseName)
    {
       return this.list();
    }
}
