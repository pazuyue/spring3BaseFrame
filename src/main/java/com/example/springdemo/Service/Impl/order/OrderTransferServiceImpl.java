package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.example.springdemo.Service.order.OrderTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderTransferServiceImpl implements OrderTransferService {

    @Autowired
    private TChannelServiceImpl tChannelService;

    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;

    @Override
    public boolean autoOrderMigration(int orderLogId) {
        TChannelOrderLog tChannelOrderLog = tChannelOrderLogService.getById(orderLogId);
        TChannel tChannel= tChannelService.getTChannelByID(tChannelOrderLog.getChannelId());
        String json = tChannelOrderLog.getContent();
        Map<String,Object> map = JSONObject.parseObject(json,new TypeReference<HashMap<String,Object>>(){});
        System.out.println("map:"+map);
        //Map order= map["trade_fullinfo_get_response"]['trade'];

        return false;
    }

}
