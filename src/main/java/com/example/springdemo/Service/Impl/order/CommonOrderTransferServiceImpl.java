package com.example.springdemo.Service.Impl.order;

import com.example.springdemo.Config.Common.Enums.orderPluginEnum;
import com.example.springdemo.Service.Plugin.OrderPluginService;
import org.springframework.stereotype.Service;

@Service(orderPluginEnum.Type.Common)
public class CommonOrderTransferServiceImpl implements OrderPluginService {
    @Override
    public boolean execute() {
        String msg = "通用转单实现";
        System.out.println(msg);
        return true;
    }
}
