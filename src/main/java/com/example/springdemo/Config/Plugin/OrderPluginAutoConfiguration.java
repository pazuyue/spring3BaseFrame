package com.example.springdemo.Config.Plugin;

import com.example.springdemo.Service.Impl.order.CommonOrderTransferServiceImpl;
import com.example.springdemo.Service.Impl.order.HmOrderTransferServiceImpl;
import com.example.springdemo.Service.Plugin.OrderPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderPluginAutoConfiguration {

    @Value("${company.companyName}")
    private String companyName;

    @Bean
    public OrderPlugin orderPlugin() {
        if ("HM".equals(companyName)) {
            System.out.println("new HmOrderTransferServiceImpl");
            return new HmOrderTransferServiceImpl();
        }
        System.out.println("new CommonOrderTransferServiceImpl");
        return new CommonOrderTransferServiceImpl();

    }
}
