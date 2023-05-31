package com.example.springdemo.Service.Impl.TChannelOrderLog;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Mapper.TChannelOrderLog.TChannelOrderLogMapper;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 渠道订单流水表(日志表) 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-05-30
 */
@Service
public class TChannelOrderLogServiceImpl extends ServiceImpl<TChannelOrderLogMapper, TChannelOrderLog> implements TChannelOrderLogService {

    public boolean SelectOrsave(TChannelOrderLog t)
    {
//构建一个查询的wrapper
        QueryWrapper<TChannelOrderLog> wrapper = new QueryWrapper<>();
        wrapper.eq("tid",t.getTid());
        TChannelOrderLog tChannelOrderLog = this.getOne(wrapper,false);
        if (Objects.isNull(tChannelOrderLog)){
          return this.save(tChannelOrderLog);
        }
        return true;
    }
}
