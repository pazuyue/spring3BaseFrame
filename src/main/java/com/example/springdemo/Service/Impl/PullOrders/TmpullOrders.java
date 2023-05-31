package com.example.springdemo.Service.Impl.PullOrders;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.PullOrders.JdpTbTradeService;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TmpullOrders {

    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;
    @Autowired
    private JdpTbTradeServiceImpl jdpTbTradeService;

    public boolean pullOrder(String startTime,String endTime) throws Exception {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sTime = simpleDateFormat.parse(startTime);
        Date eTime = simpleDateFormat.parse(endTime);
        System.out.println("dateString:"+sTime);
        System.out.println("dateString:"+eTime);
        Long tid =0L;

        while (true){
            List<JdpTbTrade> list = jdpTbTradeService.getAllByModified(sTime,eTime,tid);
            if (list.isEmpty()){
                return true;
            }
            JdpTbTrade lastElement = list.get(list.size() - 1);
            tid = lastElement.getTid();
            for (JdpTbTrade jdpTbTrade : list){
                System.out.println("tid:"+jdpTbTrade.getTid());
                TChannelOrderLog tChannelOrderLog = new TChannelOrderLog();
                tChannelOrderLog.setChannelId(9);
                tChannelOrderLog.setContent(jdpTbTrade.getJdpResponse());
                tChannelOrderLog.setTid(jdpTbTrade.getTid().toString());
                tChannelOrderLog.setOuterUpdateTime(jdpTbTrade.getModified());
                tChannelOrderLogService.save(tChannelOrderLog);
            }
        }
    }
}
