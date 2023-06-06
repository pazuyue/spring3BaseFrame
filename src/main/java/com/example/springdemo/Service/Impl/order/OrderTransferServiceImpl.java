package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.example.springdemo.Aspect.WebLogAspect;
import com.example.springdemo.Disposition.OrderDictionary;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.TChannel.TChannelRules;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Impl.OrderInfo.OrderInfoServiceImpl;
import com.example.springdemo.Service.Impl.TChannel.TChannelRulesServiceImpl;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.TChannel.TChannelRulesService;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.example.springdemo.Service.order.ChannelOrderAnalysis;
import com.example.springdemo.Service.order.OrderTransferService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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

    private final Lock lock = new ReentrantLock();


    /**
     * 自动转单入口
     * @param orderLogId
     * @return
     */
    @Override
    public  boolean autoOrderMigration(int orderLogId) {
        JSONObject order;
        String tid;
        Date payTime;
        TChannelOrderLog tChannelOrderLog = tChannelOrderLogService.getById(orderLogId);
        TChannel tChannel = tChannelService.getTChannelByID(tChannelOrderLog.getChannelId());
        String json = tChannelOrderLog.getContent();
        order = this.orderMessageParse(json,tChannel);
        tid = order.getString("tid");
        payTime = order.getDate("pay_time");
        System.out.println("tid:" + tid);
        System.out.println("pay_time:" + payTime);
        if (order.isEmpty()){
            throw new RuntimeException("报文解析异常");
        }
        int channel_id = tChannel.getChannelId();
        this.checkOrderPayTime(channel_id,payTime);
        OrderInfo orderInfo;
        lock.lock();
        try {
             orderInfo = orderInfoService.getOnlineOrderInfo(tid,channel_id);
             if (!ObjectUtils.isEmpty(orderInfo)){
                 throw new RuntimeException("订单已存在");
             }
        }finally {
            lock.unlock();
        }
        this.orderTransfer(order,tChannel);
        return true;
    }


    /**
     * 解析订单报文信息
     * @param json
     * @param tChannel
     * @return
     */
    public JSONObject orderMessageParse(String json,TChannel tChannel)
    {
        switch (tChannel.getChannelType()) {
            case OrderDictionary.TM:
                JSONObject jsonObject = JSON.parseObject(json);
                JSONObject order = jsonObject.getJSONObject("trade_fullinfo_get_response").getJSONObject("trade");
                System.out.println("order"+order);
                return order;
            default:
                throw new RuntimeException("渠道未设置");
        }
    }


    /**
     * 检查时间是否有拉单的时间内
     * @param channel_id
     * @param pay_time
     * @return
     */
    public void checkOrderPayTime(int channel_id,Date pay_time)
    {
        TChannelRules tChannelRules = tChannelRulesService.getOneByChannelID(channel_id);
        if (tChannelRules != null) {
            if (tChannelRules.getPullOrderTime().getTime()> pay_time.getTime()){
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
    public boolean orderTransfer(JSONObject order,TChannel tChannel)
    {
        System.out.println("转单实现");
        ChannelOrderAnalysis orderAnalysis;
        switch (tChannel.getChannelType()) {
            case OrderDictionary.TM:
                orderAnalysis = new TmChannelOrderAnalysis();
                Map<String,Object> orderMap = orderAnalysis.orderMessageAnalysis(order,tChannel);
                System.out.println("转单信息"+orderMap.toString());
                break;
            default:
                throw new RuntimeException("渠道未实现");
        }
        if (orderAnalysis.checkOrderOnLineState(order,tChannel) == false){

        }
        return true;
    }

}
