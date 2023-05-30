package com.example.springdemo.Service.Impl.TChannel;

import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Mapper.TChannel.TChannelMapper;
import com.example.springdemo.Service.TChannel.TChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 渠道表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@Service
public class TChannelServiceImpl extends ServiceImpl<TChannelMapper, TChannel> implements TChannelService {

    @Cacheable(cacheNames = { "TChannel" }, key = "#id")//如果缓存存在，直接读取缓存值；如果缓存不存在，则调用目标方法，并将结果放入缓存
    public TChannel getTChannel(int id)
    {
        return this.getById(id);
    }
}
