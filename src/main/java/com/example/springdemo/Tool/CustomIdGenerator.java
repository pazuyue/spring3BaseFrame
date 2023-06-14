package com.example.springdemo.Tool;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.IDTickets.IdTicketsConfig;
import com.example.springdemo.Service.Impl.IDTickets.IdTicketsConfigServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class CustomIdGenerator {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private IdTicketsConfigServiceImpl idTicketsConfigService;

    final public static int ID_INTERVAL = 10000;


    /**
     * 获取有过期时间的自增长ID
     * @param key
     * @param expireSeconds
     * @return
     */
    public long generate(String key,int expireSeconds) {
        Long id = stringRedisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
            byte[] rawKey = serializer.serialize(key);
            Long value = connection.incr(rawKey);
            connection.expire(rawKey, expireSeconds);
            return value;
        });

        return id;
    }


    public String generateOrderId(String key) {
//生成id为当前日期（yyMMddHHmmss）+6位（从000000开始不足位数补0）
        LocalDateTime now = LocalDateTime.now();
        StringBuffer stringBuffer = new StringBuffer(key);
        String orderIdPrefix = stringBuffer.append(getOrderIdPrefix(now)).toString();//生成yyyyMMddHHmmss
        ;
        String orderId = orderIdPrefix+String.format("%1$06d", generate(orderIdPrefix,20));
        return orderId;

    }

    public static String getOrderIdPrefix(LocalDateTime now){
        return now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

}
