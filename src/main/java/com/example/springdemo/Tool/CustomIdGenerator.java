package com.example.springdemo.Tool;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.IDTickets.IdTicketsConfig;
import com.example.springdemo.Service.Impl.IDTickets.IdTicketsConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class CustomIdGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IdTicketsConfigServiceImpl idTicketsConfigService;

    final public static int ID_INTERVAL = 10;

    public String getCustomId(String idType) {
        Long id;
        String key = idType + "_MAXID";
        String value = stringRedisTemplate.opsForValue().get(key);
        if (ObjectUtils.isEmpty(value)) {
            QueryWrapper<IdTicketsConfig> queryWrapper = new QueryWrapper<>();
            IdTicketsConfig idTicketsConfig = idTicketsConfigService.getOne(queryWrapper.eq("type", idType));
            if (ObjectUtils.isEmpty(idTicketsConfig)) {
                idTicketsConfig = new IdTicketsConfig();
                idTicketsConfig.setType(idType);
                idTicketsConfig.setSeq(ID_INTERVAL);
                idTicketsConfigService.saveAndCache(idTicketsConfig, key);
            }else {
                stringRedisTemplate.opsForValue().set(key, String.valueOf(idTicketsConfig.getSeq()));
            }
        }
        id = getLuaID(key, idType);
        if (ObjectUtils.isEmpty(id)) {
            idTicketsConfigService.update(idType, key);
            id = getLuaID(key, idType);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(idType).append(id);
        return stringBuffer.toString();
    }


    /**
     * Lua 获取 缓存ID
     *
     * @param key
     * @param idType
     * @return
     */
    public Long getLuaID(String key, String idType) {
        String script = "local max = tonumber(redis.call('get', KEYS[1]))\n" +
                "if max == nil then\n" +
                "return 0\n" +
                "end\n" +
                "local count = tonumber(redis.call('incr', KEYS[2]))\n" +
                "if max >= count then\n" +
                "return count\n" +
                "end";

        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        List<String> keys = Arrays.asList(key, idType);
        return (Long) stringRedisTemplate.execute(redisScript, keys);

    }
}
