package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.springdemo.Aspect.WebLogAspect;
import com.example.springdemo.Disposition.CommonDictionary;
import com.example.springdemo.Disposition.OrderDictionary;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Service.order.ChannelOrderAnalysis;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TmChannelOrderAnalysis implements ChannelOrderAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(TmChannelOrderAnalysis.class);

    @Override
    public Map<String,Object> orderMessageAnalysis(JSONObject order, TChannel channel) {
        OrderInfo orderInfo = this.getOrderInfo(order,channel);
        System.out.println(orderInfo);
        Map<String,Object> map= new  HashMap<>();
        map.put("orderInfo",orderInfo);
        return map;
    }

    /**
     * 解析主订单信息
     * @param order
     * @param channel
     * @return
     */
    @Override
    public OrderInfo getOrderInfo(JSONObject order, TChannel channel) {
        logger.info("获取订单信息");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setComeFrom(order.getString("trade_from"));
        orderInfo.setOrderType(OrderDictionary.CHANNEL_ORDER);
        Byte orderSalesType = Objects.equals(order.getString("type"), "fixed") ? OrderDictionary.ORDER_FIXED : OrderDictionary.ORDER_STEP;
        orderInfo.setOrderSalesType(orderSalesType);
        orderInfo.setChannelId(channel.getChannelId());
        orderInfo.setTid(order.getString("tid"));
        orderInfo.setShippingPaySum(order.getBigDecimal("post_fee"));
        orderInfo.setPayName("alipay");
        Map<String,Object> getOrderGoodsMap = this.getOrderGoodsCountAndActuallyPay(order.getJSONObject("orders"));
        int orderGoodsCount = (int)getOrderGoodsMap.get("orderGoodsCount");
        BigDecimal orderActuallyPaySum = (BigDecimal)getOrderGoodsMap.get("orderActuallyPaySum");
        Byte isCustomized = (Byte) getOrderGoodsMap.get("isCustomized");
        orderInfo.setOrderGoodsCount(orderGoodsCount);
        orderInfo.setOrderExpectedPaySum(order.getBigDecimal("payment"));
        orderInfo.setOrderActuallyPaySum(order.getBigDecimal("payment"));
        orderInfo.setGoodsPaySum(orderActuallyPaySum);
        BigDecimal orderDiscountSum = orderActuallyPaySum.add(order.getBigDecimal("post_fee")).subtract(order.getBigDecimal("payment"));
        orderInfo.setOrderDiscountSum(orderDiscountSum);
        orderInfo.setChannelAddTime(order.getDate("created"));
        orderInfo.setPayTime(order.getDate("pay_time"));
        orderInfo.setSellerMsg(order.getString("seller_memo"));
        orderInfo.setBuyerMsg(order.getString("buyer_message"));
        orderInfo.setNickName(order.getString("buyer_nick"));
        orderInfo.setIsCustomized(isCustomized);
        orderInfo.setUserId(order.getLong("buyer_open_uid"));
        orderInfo.setCompanyCode(channel.getCompanyCode());
        orderInfo.setVipOrderStatus(OrderDictionary.FORMAL_ORDERS);
        orderInfo.setPayStatus(OrderDictionary.ALREADY_PAY);
        orderInfo.setCreateUserCode(CommonDictionary.CREATE_SYSTEM_CODE);
        return orderInfo;
    }

    /**
     * 计算商品数量
     * @param orders
     * @return
     */
    public Map<String,Object> getOrderGoodsCountAndActuallyPay(JSONObject orders)
    {
        System.out.println("计算商品数量"+orders);
        int orderGoodsCount=0;
        BigDecimal orderActuallyPaySum =  BigDecimal.ZERO;
        Byte isCustomized = 0;
        JSONArray order =orders.getJSONArray("order");
        for (int i = 0; i < order.size(); i++) {
            int num = order.getJSONObject(i).getInteger("num");
            orderGoodsCount = orderGoodsCount+num;
            orderActuallyPaySum =orderActuallyPaySum.add(order.getJSONObject(i).getBigDecimal("price").multiply(BigDecimal.valueOf(num)));
            String customization = order.getJSONObject(i).getString("customization");
            if (StringUtils.isNotBlank(customization)){
                isCustomized = 1;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("orderGoodsCount",orderGoodsCount);
        map.put("orderActuallyPaySum",orderActuallyPaySum);
        map.put("isCustomized",isCustomized);
        return map;
    }
}
