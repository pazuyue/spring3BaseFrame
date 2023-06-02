package com.example.springdemo.Service.order;

import com.alibaba.fastjson2.JSONObject;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.TChannel.TChannel;

import java.util.Map;

public interface ChannelOrderAnalysis {
    /**
     *
     * @param order
     * @param channel
     * @return
     */
    public  Map<String,Object> orderMessageAnalysis(JSONObject order, TChannel channel) ;

    public OrderInfo getOrderInfo(JSONObject order, TChannel channel);
}
