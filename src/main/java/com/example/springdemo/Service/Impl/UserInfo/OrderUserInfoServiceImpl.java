package com.example.springdemo.Service.Impl.UserInfo;

import com.example.springdemo.Entity.UserInfo.OrderUserInfo;
import com.example.springdemo.Mapper.OrderUser.OrderUserInfoMapper;
import com.example.springdemo.Service.UserInfo.OrderUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderUserInfoServiceImpl extends ServiceImpl<OrderUserInfoMapper, OrderUserInfo> implements OrderUserInfoService {


    @Transactional
    public int saveOrderUserInfo(OrderUserInfo orderUserInfo){
        return this.baseMapper.insert(orderUserInfo);
    }

}
