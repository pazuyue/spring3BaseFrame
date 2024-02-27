package com.example.springdemo.Service.Impl.order;

import com.example.springdemo.Service.Plugin.OrderPlugin;
import org.springframework.stereotype.Service;

@Service
public class HmOrderTransferServiceImpl implements OrderPlugin {
    @Override
    public boolean execute() {
        String msg = "HM转单实现";
        System.out.println(msg);
        return true;
    }
}
