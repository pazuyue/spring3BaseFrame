package com.example.springdemo.Service.order;

public interface OrderTransferService {

    /**
     * 自动转单
     * @param orderLogId
     * @return
     */
    public boolean autoOrderMigration(int orderLogId);
}
