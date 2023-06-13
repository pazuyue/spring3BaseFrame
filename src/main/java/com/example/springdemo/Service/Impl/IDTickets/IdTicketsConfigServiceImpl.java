package com.example.springdemo.Service.Impl.IDTickets;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.example.springdemo.Entity.IDTickets.IdTicketsConfig;
import com.example.springdemo.Mapper.IDTickets.IdTicketsConfigMapper;
import com.example.springdemo.Service.IDTickets.IdTicketsConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.springdemo.Tool.CustomIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 编码生成表 服务实现类
 * </p>
 *
 * @author 月光光
 * @since 2023-06-07
 */
@Service
public class IdTicketsConfigServiceImpl extends ServiceImpl<IdTicketsConfigMapper, IdTicketsConfig> implements IdTicketsConfigService {

    @Autowired
    private RedisTemplate redisTemplate;
    private final Lock lock = new ReentrantLock();

    public synchronized boolean update(String type, String key) {

        QueryWrapper<IdTicketsConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", type);
        IdTicketsConfig idTicketsConfig = this.getOne(queryWrapper);
        if (!ObjectUtils.isEmpty(idTicketsConfig)) {
            lock.lock();
            try {
                idTicketsConfig.setSeq(idTicketsConfig.getSeq() + CustomIdGenerator.ID_INTERVAL);
                if (this.updateById(idTicketsConfig)) {
                    redisTemplate.opsForValue().set(key, idTicketsConfig.getSeq());
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    /**
     * 获取主库ID信息
     *
     * @param idTicketsConfig
     * @param key
     * @return
     */
    public boolean saveAndCache(IdTicketsConfig idTicketsConfig, String key) {
        lock.lock();
        try {
            if (this.save(idTicketsConfig)) {
                redisTemplate.opsForValue().set(key, idTicketsConfig.getSeq());
                return true;
            }
        } finally {
            lock.unlock();
        }
        throw new RuntimeException(key + "获取主库ID信息失败");
    }

}
