package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSON;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.order.OrderTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderTransferServiceImpl implements OrderTransferService {

    @Autowired
    private TChannelServiceImpl tChannelService;

    @Override
    public boolean autoOrderMigration(int channelId, int orderLogId) {
        TChannel tChannel= tChannelService.getTChannel(channelId);
        //Map map = JSON.parseObject(data, Map.class);
        return false;
    }

}
