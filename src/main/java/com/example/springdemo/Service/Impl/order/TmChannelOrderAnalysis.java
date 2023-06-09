package com.example.springdemo.Service.Impl.order;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.example.springdemo.Aspect.WebLogAspect;
import com.example.springdemo.Disposition.CommonDictionary;
import com.example.springdemo.Disposition.OrderDictionary;
import com.example.springdemo.Entity.GoodsInfo.GoodsColor;
import com.example.springdemo.Entity.GoodsInfo.GoodsSize;
import com.example.springdemo.Entity.GoodsInfo.GoodsSkuSnInfo;
import com.example.springdemo.Entity.OrderInfo.OrderGoods;
import com.example.springdemo.Entity.OrderInfo.OrderInfo;
import com.example.springdemo.Entity.PullOrders.JdpTbTrade;
import com.example.springdemo.Entity.TChannel.TChannel;
import com.example.springdemo.Entity.UserInfo.OrderUserAddress;
import com.example.springdemo.Entity.UserInfo.OrderUserInfo;
import com.example.springdemo.Service.Impl.GoodsInfo.GoodsColorServiceImpl;
import com.example.springdemo.Service.Impl.GoodsInfo.GoodsSizeServiceImpl;
import com.example.springdemo.Service.Impl.GoodsInfo.GoodsSkuSnInfoServiceImpl;
import com.example.springdemo.Service.Impl.PullOrders.JdpTbTradeServiceImpl;
import com.example.springdemo.Service.order.ChannelOrderAnalysis;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;

@Component
public class TmChannelOrderAnalysis implements ChannelOrderAnalysis {

    private static final Logger logger = LoggerFactory.getLogger(TmChannelOrderAnalysis.class);

    @Autowired
    private GoodsSkuSnInfoServiceImpl goodsSkuSnInfoService;
    @Autowired
    private GoodsSizeServiceImpl goodsSizeService;
    @Autowired
    private GoodsColorServiceImpl goodsColorService;
    @Autowired
    private JdpTbTradeServiceImpl jdpTbTradeService;

    @Override
    public Map<String,Object> orderMessageAnalysis(JSONObject order, TChannel channel) {
        OrderInfo orderInfo = this.getOrderInfo(order,channel);
        OrderUserInfo orderUserInfo = this.getOrderUserInfo(order,channel);
        OrderUserAddress orderUserAddress = this.getOrderUserAddress(order,channel);
        OrderGoods orderGoods = new OrderGoods();
        System.out.println(orderInfo);
        Map<String,Object> map= new  HashMap<>();
        map.put("orderInfo",orderInfo);
        map.put("orderUserInfo",orderUserInfo);
        map.put("orderUserAddress",orderUserAddress);
        map.put("orderGoods",orderGoods);
        return map;
    }

    /**
     * 解析主订单信息
     * @param order
     * @param channel
     * @return
     */
    @Override
    public OrderInfo getOrderInfo(JSONObject order, TChannel channel) {
        logger.info("获取订单信息");
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setComeFrom(order.getString("trade_from"));
        orderInfo.setOrderType(OrderDictionary.CHANNEL_ORDER);
        Byte orderSalesType = Objects.equals(order.getString("type"), "fixed") ? OrderDictionary.ORDER_FIXED : OrderDictionary.ORDER_STEP;
        orderInfo.setOrderSalesType(orderSalesType);
        orderInfo.setChannelId(channel.getChannelId());
        orderInfo.setTid(order.getString("tid"));
        orderInfo.setShippingPaySum(order.getBigDecimal("post_fee"));
        orderInfo.setPayName("alipay");
        Map<String,Object> getOrderGoodsMap = this.getOrderGoodsCountAndActuallyPay(order.getJSONObject("orders"));
        int orderGoodsCount = (int)getOrderGoodsMap.get("orderGoodsCount");
        BigDecimal orderActuallyPaySum = (BigDecimal)getOrderGoodsMap.get("orderActuallyPaySum");
        Byte isCustomized = (Byte) getOrderGoodsMap.get("isCustomized");
        orderInfo.setOrderGoodsCount(orderGoodsCount);
        orderInfo.setOrderExpectedPaySum(order.getBigDecimal("payment"));
        orderInfo.setOrderActuallyPaySum(order.getBigDecimal("payment"));
        orderInfo.setGoodsPaySum(orderActuallyPaySum);
        BigDecimal orderDiscountSum = orderActuallyPaySum.add(order.getBigDecimal("post_fee")).subtract(order.getBigDecimal("payment"));
        orderInfo.setOrderDiscountSum(orderDiscountSum);
        orderInfo.setChannelAddTime(order.getDate("created"));
        orderInfo.setPayTime(order.getDate("pay_time"));
        orderInfo.setSellerMsg(order.getString("seller_memo"));
        orderInfo.setBuyerMsg(order.getString("buyer_message"));
        orderInfo.setNickName(order.getString("buyer_nick"));
        orderInfo.setIsCustomized(isCustomized);
        orderInfo.setUserId(order.getString("buyer_open_uid"));
        orderInfo.setCompanyCode(channel.getCompanyCode());
        orderInfo.setVipOrderStatus(OrderDictionary.FORMAL_ORDERS);
        orderInfo.setPayStatus(OrderDictionary.ALREADY_PAY);
        orderInfo.setCreateUserCode(CommonDictionary.CREATE_SYSTEM_CODE);
        return orderInfo;
    }

    public List<OrderGoods> getOrderGoods(String tid,JSONObject orders, TChannel channel)
    {
        JSONArray order =orders.getJSONArray("order");
        List<OrderGoods> goods = new ArrayList<>();
        Byte isCustomized = 0;
        for (int i = 0; i < order.size(); i++) {
            OrderGoods orderGoods = new OrderGoods();
            int num = order.getJSONObject(i).getInteger("num");
            String skuSn = order.getJSONObject(i).getString("outer_iid");
            GoodsSkuSnInfo goodsSkuSnInfo = goodsSkuSnInfoService.getOneByskuSn(skuSn,channel.getCompanyCode());
            orderGoods.setTid(tid);
            orderGoods.setChannelId(channel.getChannelId());
            orderGoods.setGoodsSn(goodsSkuSnInfo.getSkuSn());
            orderGoods.setSkuid(order.getJSONObject(i).getString("sku_id"));
            orderGoods.setSkuSn(skuSn);
            orderGoods.setBarcodeSn(goodsSkuSnInfo.getBarcodeSn());
            orderGoods.setGoodsName(goodsSkuSnInfo.getGoodsName());
            GoodsSize goodsSize = goodsSizeService.getOneBySizeCode(goodsSkuSnInfo.getSizeCode());
            orderGoods.setSizeName(goodsSize.getSizeName());

            GoodsColor goodsColor = goodsColorService.getOneByColorCode(goodsSkuSnInfo.getColorCode());
            orderGoods.setColorName(goodsColor.getColorName());
            StringBuilder goodsAttr = new StringBuilder();
            goodsAttr.append(goodsSize.getSizeName());
            goodsAttr.append(":");
            goodsAttr.append(goodsColor.getColorName());
            orderGoods.setGoodsAttr(goodsAttr.toString());
            orderGoods.setGoodsNumber(num);
            orderGoods.setMarketPrice(order.getJSONObject(i).getBigDecimal("price"));
            orderGoods.setSumMarketPrice(order.getJSONObject(i).getBigDecimal("price").add(BigDecimal.valueOf(num)));
            orderGoods.setSumDiscount(order.getJSONObject(i).getBigDecimal("sum_discount"));
            orderGoods.setDiscount(orderGoods.getSumDiscount().divide(BigDecimal.valueOf(num)));
            orderGoods.setTransactionPrice(order.getJSONObject(i).getBigDecimal("divide_order_fee"));
            orderGoods.setTransactionPrice(orderGoods.getSumTransactionPrice().divide(BigDecimal.valueOf(num)));
            orderGoods.setOid(order.getJSONObject(i).getString("oid"));
            orderGoods.setUniqueCode(order.getJSONObject(i).getString("oid"));
            String customization = order.getJSONObject(i).getString("customization");
            if (StringUtils.isNotBlank(customization)){
                isCustomized = 1;
            }
            orderGoods.setIsCustomized(isCustomized);
            orderGoods.setCompanyCode(channel.getCompanyCode());
            goods.add(orderGoods);
        }
        return goods;
    }

    /**
     * 获取订单用户信息
     * @param order
     * @param channel
     * @return
     */
    public OrderUserInfo getOrderUserInfo(JSONObject order, TChannel channel)
    {
        logger.info("获取订单用户信息");
        OrderUserInfo orderUserInfo = new OrderUserInfo();
        orderUserInfo.setUserId(order.getString("buyer_open_uid"));
        orderUserInfo.setUserName(order.getString("buyer_nick"));
        orderUserInfo.setNickName(order.getString("buyer_nick"));
        orderUserInfo.setRealName(order.getString("buyer_nick"));
        orderUserInfo.setEmail(order.getString("seller_email"));
        orderUserInfo.setMobile(order.getString("receiver_mobile"));
        orderUserInfo.setCompanyCode(channel.getCompanyCode());
        return orderUserInfo;
    }

    /**
     * 获取订单地址信息
     * @param order
     * @param channel
     * @return
     */
    public OrderUserAddress getOrderUserAddress(JSONObject order, TChannel channel){
        logger.info("获取订单地址信息");
        OrderUserAddress orderUserAddress = new OrderUserAddress();
        orderUserAddress.setUserId(order.getLong("buyer_open_uid"));
        orderUserAddress.setConsignee(order.getString("receiver_name"));
        orderUserAddress.setCountry(CommonDictionary.COUNTRY);
        orderUserAddress.setProvince(order.getString("receiver_state"));
        String city = StringUtils.isNotBlank(order.getString("receiver_city")) ? order.getString("receiver_state") : order.getString("receiver_city");
        orderUserAddress.setCity(city);
        orderUserAddress.setDistrict(order.getString("receiver_district"));
        orderUserAddress.setTown(order.getString("receiver_town"));
        StringBuilder address = new StringBuilder();
        address.append(order.getString("receiver_town"));
        address.append(order.getString("receiver_address"));
        orderUserAddress.setAddress(address.toString());
        orderUserAddress.setZipcode(order.getString("receiver_zip"));
        orderUserAddress.setEmail(order.getString("seller_email"));
        orderUserAddress.setMobile(order.getString("receiver_mobile"));
        orderUserAddress.setTel(order.getString("receiver_mobile"));
        //orderUserAddress.setCompanyCode(channel.getCompanyCode());
        return orderUserAddress;
    }

    @Override
    public boolean checkOrderOnLineState(JSONObject order, TChannel channel) {
        String tid = order.getString("tid");
        System.out.println("checkOrderOnLineState_tid:"+tid);
        if (ObjectUtils.isEmpty(jdpTbTradeService)) {
            System.out.println("jdpTbTradeService is empty");
            return false;
        }
        JdpTbTrade jdpTbTrade = jdpTbTradeService.getOneByTid(tid,"tid","status");
        String status = order.getString("status");
        System.out.println("checkOrderOnLineState_status:"+status);
        if (!Objects.equals(status, "WAIT_SELLER_SEND_GOODS"))
            return false;
        if (!Objects.equals(jdpTbTrade.getStatus(), "WAIT_SELLER_SEND_GOODS"))
            return false;

        return true;
    }

    /**
     * 计算商品数量
     * @param orders
     * @return
     */
    public Map<String,Object> getOrderGoodsCountAndActuallyPay(JSONObject orders)
    {
        logger.info("计算商品数量"+orders);
        int orderGoodsCount=0;
        BigDecimal orderActuallyPaySum =  BigDecimal.ZERO;
        Byte isCustomized = 0;
        JSONArray order =orders.getJSONArray("order");
        for (int i = 0; i < order.size(); i++) {
            int num = order.getJSONObject(i).getInteger("num");
            orderGoodsCount = orderGoodsCount+num;
            orderActuallyPaySum =orderActuallyPaySum.add(order.getJSONObject(i).getBigDecimal("price").multiply(BigDecimal.valueOf(num)));
            String customization = order.getJSONObject(i).getString("customization");
            if (StringUtils.isNotBlank(customization)){
                isCustomized = 1;
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("orderGoodsCount",orderGoodsCount);
        map.put("orderActuallyPaySum",orderActuallyPaySum);
        map.put("isCustomized",isCustomized);
        return map;
    }
}
