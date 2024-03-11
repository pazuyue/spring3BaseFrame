package com.example.springdemo.Config.Common.Factory;

import com.example.springdemo.Service.Plugin.OrderPluginService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderPluginFactory {
    @Resource
    private Map<String, OrderPluginService> serviceMap = new ConcurrentHashMap<>();

    public OrderPluginService getBean(String type) {
        return serviceMap.get(type);
    }

}
