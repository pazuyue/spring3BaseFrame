package com.example.springdemo.Service.Impl.OrderInfo;

import com.example.springdemo.Entity.OrderInfo.OrderAction;
import com.example.springdemo.Mapper.OrderInfo.OrderActionMapper;
import com.example.springdemo.Service.OrderInfo.OrderActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 对订单操作日志表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderActionServiceImpl extends ServiceImpl<OrderActionMapper, OrderAction> implements OrderActionService {

}
