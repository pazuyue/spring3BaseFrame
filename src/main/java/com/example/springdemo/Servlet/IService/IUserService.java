package com.example.springdemo.Servlet.IService;

import com.example.springdemo.Pojo.User;

import java.util.List;

public interface IUserService {
    /**
     * 查找所有
     * @return
     */
    List<User> getAll();
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getOne(Long id);
    /**
     * 新增用户
     * @param user
     */
    void insertUser(User user);
    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);

    void deleteAll1();

    void deleteAll12();
}
