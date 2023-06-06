package com.example.springdemo.Service.Impl.UserInfo;

import com.example.springdemo.Entity.UserInfo.OrderUserAddress;
import com.example.springdemo.Mapper.OrderUser.OrderUserAddressMapper;
import com.example.springdemo.Service.UserInfo.OrderUserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderUserAddressServiceImpl extends ServiceImpl<OrderUserAddressMapper, OrderUserAddress> implements OrderUserAddressService {

}
