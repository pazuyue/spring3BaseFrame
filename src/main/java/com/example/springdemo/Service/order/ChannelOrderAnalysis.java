package com.example.springdemo.Service.order;

import com.alibaba.fastjson2.JSONObject;
import com.example.springdemo.Entity.OrderInfo.OrderGoods;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.UserInfo.OrderUserAddress;
import com.example.springdemo.Entity.UserInfo.OrderUserInfo;

import java.util.List;
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

    public List<OrderGoods> getOrderGoods(String tid,JSONObject orders, TChannel channel);

    public OrderUserInfo getOrderUserInfo(JSONObject order, TChannel channel);

    public OrderUserAddress getOrderUserAddress(JSONObject order, TChannel channel);

    public boolean checkOrderOnLineState(JSONObject order,TChannel channel);
}
