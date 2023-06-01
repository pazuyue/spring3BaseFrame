package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.springdemo.Aspect.WebLogAspect;
import com.example.springdemo.Disposition.OrderDictionary;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.example.springdemo.Service.order.OrderTransferService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderTransferServiceImpl implements OrderTransferService {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private TChannelServiceImpl tChannelService;

    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;

    @Override
    public boolean autoOrderMigration(int orderLogId) {
        TChannelOrderLog tChannelOrderLog = tChannelOrderLogService.getById(orderLogId);
        TChannel tChannel = tChannelService.getTChannelByID(tChannelOrderLog.getChannelId());
        JSONObject order = null;
        String tid = null;
        Date payTime = null;
        switch (tChannel.getChannelType()) {
            case OrderDictionary.TM:
                String json = tChannelOrderLog.getContent();
                JSONObject jsonObject = JSON.parseObject(json);
                order = jsonObject.getJSONObject("trade_fullinfo_get_response").getJSONObject("trade");
                System.out.println("order"+order);
                tid = order.getString("tid");
                payTime = order.getDate("pay_time");
                System.out.println("tid:" + tid);
                System.out.println("pay_time:" + payTime);
                break;
        }
        if (order.isEmpty()){
            throw new RuntimeException("报文解析异常");
        }

        return false;
    }

}
