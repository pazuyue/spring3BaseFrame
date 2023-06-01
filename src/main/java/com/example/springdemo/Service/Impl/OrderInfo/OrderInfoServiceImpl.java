package com.example.springdemo.Service.Impl.OrderInfo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Mapper.OrderInfo.OrderInfoMapper;
import com.example.springdemo.Service.OrderInfo.OrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单主信息表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {

    public OrderInfo getOnlineOrderInfo(String tid,int channelID){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tid",tid);
        queryWrapper.eq("channel_id",channelID);
        queryWrapper.eq("order_type",1);
        return this.getOne(queryWrapper);
    }
}
