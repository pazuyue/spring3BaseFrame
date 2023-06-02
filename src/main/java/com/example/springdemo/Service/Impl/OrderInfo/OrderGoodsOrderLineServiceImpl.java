package com.example.springdemo.Service.Impl.OrderInfo;

import com.example.springdemo.Entity.OrderInfo.OrderGoodsOrderLine;
import com.example.springdemo.Mapper.OrderInfo.OrderGoodsOrderLineMapper;
import com.example.springdemo.Service.OrderInfo.OrderGoodsOrderLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单商品明细表:ol级 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Service
public class OrderGoodsOrderLineServiceImpl extends ServiceImpl<OrderGoodsOrderLineMapper, OrderGoodsOrderLine> implements OrderGoodsOrderLineService {

}
