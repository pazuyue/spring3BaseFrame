package com.example.springdemo.Service.Impl.order;

import com.example.springdemo.Service.Plugin.OrderPlugin;
import org.springframework.stereotype.Service;

@Service
public class CommonOrderTransferServiceImpl implements OrderPlugin {
    @Override
    public boolean execute() {
        String msg = "通用转单实现";
        System.out.println(msg);
        return true;
    }
}
