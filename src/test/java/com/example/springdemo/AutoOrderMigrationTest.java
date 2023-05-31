package com.example.springdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannelOrderLog.TChannelOrderLog;
import com.example.springdemo.Service.Impl.PullOrders.JdpTbTradeServiceImpl;
import com.example.springdemo.Service.Impl.PullOrders.TmpullOrders;
import com.example.springdemo.Service.Impl.order.OrderTransferServiceImpl;
import com.example.springdemo.Service.PullOrders.JdpTbTradeService;
import com.example.springdemo.Service.TChannelOrderLog.TChannelOrderLogService;
import com.github.pagehelper.PageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class AutoOrderMigrationTest {

    @Autowired
    private OrderTransferServiceImpl orderTransferService;
    @Autowired
    private JdpTbTradeService jdpTbTradeService;
    @Autowired
    private TChannelOrderLogService tChannelOrderLogService;

    @Autowired
    private TmpullOrders tmpullOrders;

    @Test
    public void autoOrderMigration()
    {
        orderTransferService.autoOrderMigration(9,1);
    }

    @Test
    public void selectJdpTbTrade()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023,Calendar.MAY,30,18,00);
        Date date = calendar.getTime();
        System.out.println("calendar:"+date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        System.out.println("dateString:"+dateString);
        // Step1：创建一个 QueryWrapper 对象
        QueryWrapper<JdpTbTrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("modified",dateString).last("LIMIT 1000");
        List<JdpTbTrade> list = jdpTbTradeService.list(queryWrapper);
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

    @Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2023,Calendar.MAY,30,18,00);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = sdf.format(date);
        System.out.println(dateString);
    }

    @Test
    public void testPullOrder(){
        String startTime="2023-05-30 00:00:00";
        String endTime = "2023-05-31 00:00:00";
        try {
            tmpullOrders.pullOrder(startTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdate()
    {
       TChannelOrderLog tChannelOrderLog =tChannelOrderLogService.getById(2);
       tChannelOrderLog.setChannelId(9);
       tChannelOrderLogService.updateById(tChannelOrderLog);
    }
}
