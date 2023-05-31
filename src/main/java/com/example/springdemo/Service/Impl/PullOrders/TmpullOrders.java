package com.example.springdemo.Service.Impl.PullOrders;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.Config.RabbitMq.MQProperties;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import com.example.springdemo.Service.Impl.TChannelOrderLog.TChannelOrderLogServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TmpullOrders {

    @Autowired
    private TChannelOrderLogServiceImpl tChannelOrderLogService;
    @Autowired
    private JdpTbTradeServiceImpl jdpTbTradeService;
    @Autowired
    private TChannelServiceImpl tChannelService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MQProperties mqProperties;

    public boolean pullOrder(String startTime,String endTime) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sTime = simpleDateFormat.parse(startTime);
        Date eTime = simpleDateFormat.parse(endTime);
        System.out.println("dateString:"+sTime);
        System.out.println("dateString:"+eTime);
        int page =1;
        int pageSize=10;

        while (true){
            Page<JdpTbTrade> pages = jdpTbTradeService.getAllByModified(sTime,eTime,page,pageSize);
            page ++;
            System.out.println("page"+page);
            List<JdpTbTrade> list = pages.getRecords();
            for (JdpTbTrade jdpTbTrade : list){
                System.out.println("tid:"+jdpTbTrade.getTid());
                TChannelOrderLog tChannelOrderLog = new TChannelOrderLog();
                TChannel tChannel = tChannelService.getTChannelByName(jdpTbTrade.getSellerNick());
                tChannelOrderLog.setChannelId(tChannel.getChannelId());
                tChannelOrderLog.setContent(jdpTbTrade.getJdpResponse());
                tChannelOrderLog.setTid(jdpTbTrade.getTid().toString());
                tChannelOrderLog.setOuterUpdateTime(jdpTbTrade.getModified());
                tChannelOrderLogService.SelectOrsave(tChannelOrderLog);

                rabbitTemplate.convertAndSend(mqProperties.getDefaultExchange(),
                        "orderMigrationKey", "发送了一条信息");
            }
            if (!pages.hasNext()){
                return true;
            }
        }
    }
}
