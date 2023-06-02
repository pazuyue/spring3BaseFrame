package com.example.springdemo.Service.Impl.TChannel;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.TChannel.TChannelRules;
import com.example.springdemo.Mapper.TChannel.TChannelRulesMapper;
import com.example.springdemo.Service.TChannel.TChannelRulesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道规则表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-01
 */
@Service
public class TChannelRulesServiceImpl extends ServiceImpl<TChannelRulesMapper, TChannelRules> implements TChannelRulesService {

    public TChannelRules getOneByChannelID(int channelID){
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("channel_id",channelID);
        return this.getOne(queryWrapper);
    }

}
