package com.example.springdemo.Service.Impl.PullOrders;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Mapper.PullOrders.JdpTbTradeMapper;
import com.example.springdemo.Service.PullOrders.JdpTbTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-05-31
 */
@Service
@DS("sys_info")
public class JdpTbTradeServiceImpl extends ServiceImpl<JdpTbTradeMapper, JdpTbTrade> implements JdpTbTradeService {

}
