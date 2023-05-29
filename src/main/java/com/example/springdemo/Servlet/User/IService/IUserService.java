package com.example.springdemo.Servlet.User.IService;

import com.example.springdemo.Pojo.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface IUserService {
    /**
     * 查找所有
     * @return
     */
    public PageInfo<User> getUserList(int pageNum, int pageSize);
    /**
     * 根据id获取用户
     * @param id
     * @return
     */
    User getOne(Integer id);
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

    void deleteAll();

}
