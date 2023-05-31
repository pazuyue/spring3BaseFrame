package com.example.springdemo;

import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Service.Impl.TChannel.TChannelServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class TChannelTest {

    @Autowired
    private TChannelServiceImpl tChannelService;

    @Test
    public void getTChannelByNameTest()
    {
       TChannel tChannel = tChannelService.getTChannelByName("ECCO爱步官方旗舰店");
       System.out.println("tChannel:"+tChannel.toString());
    }
}
