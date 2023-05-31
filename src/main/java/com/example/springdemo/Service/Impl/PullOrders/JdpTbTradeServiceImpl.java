package com.example.springdemo.Service.Impl.PullOrders;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Mapper.PullOrders.JdpTbTradeMapper;
import com.example.springdemo.Service.PullOrders.JdpTbTradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    public Page<JdpTbTrade> getAllByModified(Date sTime,Date eTime,Integer page,Integer pageSize)
    {
        QueryWrapper<JdpTbTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("modified",sTime)
                .le("modified",eTime);
        Page<JdpTbTrade> aPage = this.page(new Page<>(page, pageSize),queryWrapper);
        return aPage;
    }
}
