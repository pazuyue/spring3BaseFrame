package com.example.springdemo.Service.Impl.order;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.example.commonadvice.tool.SnowflakeIdGenerator;
import com.example.springdemo.Aspect.WebLogAspect;
import com.example.springdemo.Disposition.OrderDictionary;
import com.example.springdemo.Entity.OrderInfo.OrderGoods;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.TChannel.TChannelRules;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Entity.UserInfo.OrderUserAddress;
import com.example.springdemo.Entity.UserInfo.OrderUserInfo;
import com.example.springdemo.Service.Impl.OrderInfo.OrderGoodsServiceImpl;
import com.example.springdemo.Service.Impl.OrderInfo.OrderInfoServiceImpl;
import com.example.springdemo.Service.Impl.TChannel.TChannelRulesServiceImpl;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.Impl.UserInfo.OrderUserAddressServiceImpl;
import com.example.springdemo.Service.Impl.UserInfo.OrderUserInfoServiceImpl;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.example.springdemo.Service.order.ChannelOrderAnalysis;
import com.example.springdemo.Service.order.OrderTransferService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class OrderTransferServiceImpl implements OrderTransferService {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Autowired
    private TChannelServiceImpl tChannelService;
    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;
    @Autowired
    private TChannelRulesServiceImpl tChannelRulesService;
    @Autowired
    private OrderInfoServiceImpl orderInfoService;
    @Resource
    private SnowflakeIdGenerator snowflakeIdGenerator;
    @Autowired
    private OrderUserInfoServiceImpl orderUserInfoService;
    @Autowired
    private OrderUserAddressServiceImpl orderUserAddressService;
    @Autowired
    private OrderGoodsServiceImpl orderGoodsService;
    @Autowired
    private TmChannelOrderAnalysis tmChannelOrderAnalysis;

    private final Lock lock = new ReentrantLock();

    /**
     * 自动转单入口
     *
     * @param orderLogId
     * @return
     */
    @Override
    public boolean autoOrderMigration(int orderLogId) {
        JSONObject order;
        String tid;
        Date payTime;
        TChannelOrderLog tChannelOrderLog = tChannelOrderLogService.getById(orderLogId);
        TChannel tChannel = tChannelService.getTChannelByID(tChannelOrderLog.getChannelId());
        String json = tChannelOrderLog.getContent();
        order = this.orderMessageParse(json, tChannel);
        tid = order.getString("tid");
        payTime = order.getDate("pay_time");
        if (order.isEmpty()) {
            throw new RuntimeException("报文解析异常");
        }
        int channel_id = tChannel.getChannelId();
        this.checkOrderPayTime(channel_id, payTime);
        OrderInfo orderInfo;
        lock.lock();
        try {
            orderInfo = orderInfoService.getOnlineOrderInfo(tid, channel_id);
            if (!ObjectUtils.isEmpty(orderInfo))
                throw new RuntimeException("订单已存在");
        } finally {
            lock.unlock();
        }
        this.orderTransfer(order, tChannel);
        return true;
    }


    /**
     * 解析订单报文信息
     *
     * @param json
     * @param tChannel
     * @return
     */
    public JSONObject orderMessageParse(String json, TChannel tChannel) {
        switch (tChannel.getChannelType()) {
            case OrderDictionary.TM:
                JSONObject jsonObject = JSON.parseObject(json);
                JSONObject order = jsonObject.getJSONObject("trade_fullinfo_get_response").getJSONObject("trade");
                System.out.println("order" + order);
                return order;
            default:
                throw new RuntimeException("渠道未设置");
        }
    }


    /**
     * 检查时间是否有拉单的时间内
     *
     * @param channel_id
     * @param pay_time
     * @return
     */
    public void checkOrderPayTime(int channel_id, Date pay_time) {
        TChannelRules tChannelRules = tChannelRulesService.getOneByChannelID(channel_id);
        if (tChannelRules != null) {
            if (tChannelRules.getPullOrderTime().getTime() > pay_time.getTime()) {
                throw new RuntimeException("检查时间不通过");
            }
        }
    }

    /**
     * 转单实现
     * @param order
     * @param tChannel
     * @return
     */
    @Transactional
    public boolean orderTransfer(JSONObject order, TChannel tChannel) {
        System.out.println("转单实现");
        ChannelOrderAnalysis orderAnalysis;
        Map<String, Object> orderMap;
        switch (tChannel.getChannelType()) {
            case OrderDictionary.TM:
                orderAnalysis = tmChannelOrderAnalysis;
                orderMap = orderAnalysis.orderMessageAnalysis(order, tChannel);
                System.out.println("转单信息" + orderMap.toString());
                break;
            default:
                throw new RuntimeException("渠道未实现");
        }
        if (orderAnalysis.checkOrderOnLineState(order, tChannel)) {
            String orderSn = "XS"+snowflakeIdGenerator.nextId();
            if (ObjectUtils.isEmpty(orderSn))
                throw new RuntimeException("获取订单失败");
            SpringUtil.getBean(OrderTransferService.class).saveOrderInfoCore(orderMap,orderSn);
        }
        return true;
    }

    /**
     * 保存订单信息
     *
     * @param orderMap
     * @return
     */
    @Transactional
    @Override
    public boolean saveOrderInfoCore(Map<String, Object> orderMap, String orderSn) {
        OrderInfo orderInfo = (OrderInfo) orderMap.get("orderInfo");
        OrderUserInfo orderUserInfo = (OrderUserInfo) orderMap.get("orderUserInfo");
        OrderUserAddress orderUserAddress = (OrderUserAddress) orderMap.get("orderUserAddress");
        OrderGoods orderGoods = (OrderGoods) orderMap.get("orderGoods");
        orderInfo.setOrderSn(orderSn);
        orderGoods.setOrderSn(orderSn);
           int userID = orderUserInfoService.saveOrderUserInfo(orderUserInfo);
           if (userID>0) {
               orderInfo.setUserId(String.valueOf(userID));
               orderInfoService.save(orderInfo);
               orderUserAddressService.save(orderUserAddress);
               orderGoodsService.save(orderGoods);
           }
        return true;
    }

}
