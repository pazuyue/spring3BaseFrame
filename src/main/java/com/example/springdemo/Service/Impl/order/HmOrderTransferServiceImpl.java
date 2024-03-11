package com.example.springdemo.Service.Impl.order;

import com.example.springdemo.Config.Common.Enums.orderPluginEnum;
import com.example.springdemo.Service.Plugin.OrderPluginService;
import org.springframework.stereotype.Service;

@Service(orderPluginEnum.Type.HM)
public class HmOrderTransferServiceImpl implements OrderPluginService {
    @Override
    public boolean execute() {
        String msg = "HM转单实现";
        System.out.println(msg);
        return true;
    }
}
