package com.example.springdemo.Service.Impl.OrderInfo;

import com.example.springdemo.Entity.OrderInfo.OrderInfoDetail;
import com.example.springdemo.Mapper.OrderInfo.OrderInfoDetailMapper;
import com.example.springdemo.Service.OrderInfo.OrderInfoDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单详情表（地址） 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderInfoDetailServiceImpl extends ServiceImpl<OrderInfoDetailMapper, OrderInfoDetail> implements OrderInfoDetailService {

}
