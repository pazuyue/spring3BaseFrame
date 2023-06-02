package com.example.springdemo.Disposition;

import lombok.Data;

@Data
public class OrderDictionary {
    public static final String TM = "1";


    public static final Byte CHANNEL_ORDER = 1; //渠道订单

    public static final Byte ORDER_FIXED = 1;//款到发货
    public static final Byte ORDER_STEP = 4;//预售订单

    public static final Byte INSTANT_ORDERS = 1;//即时订单
    public static final Byte WAREHOUSE_FINDING_ORDERS = 2;//寻仓单
    public static final Byte FORMAL_ORDERS = 3;//正式单

    public static final Byte ALREADY_PAY =2; //已经支付



}
