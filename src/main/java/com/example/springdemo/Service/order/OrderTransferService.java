package com.example.springdemo.Service.order;

import java.util.Map;

public interface OrderTransferService {

    /**
     * 自动转单
     * @param orderLogId
     * @return
     */
    public boolean autoOrderMigration(int orderLogId);

    public boolean saveOrderInfoCore(Map<String, Object> orderMap, String orderSn);
}
