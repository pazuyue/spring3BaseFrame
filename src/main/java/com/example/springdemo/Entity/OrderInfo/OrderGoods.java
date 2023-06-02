package com.example.springdemo.Entity.OrderInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单商品明细表：oid级别
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_goods")
public class OrderGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * OMS订单号，order_info.order_sn
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 外部订单号
     */
    @TableField("tid")
    private String tid;

    /**
     * 问题单状态 （1 否  2 是）
     */
    @TableField("question_status")
    private Byte questionStatus;

    /**
     *  商品SKU
     */
    @TableField("sku_sn")
    private String skuSn;

    /**
     * 尺码，goods_size.size_name
     */
    @TableField("size_name")
    private String sizeName;

    /**
     * 颜色名，goods_color.color_name
     */
    @TableField("color_name")
    private String colorName;

    /**
     * 货号
     */
    @TableField("goods_sn")
    private String goodsSn;

    /**
     * 条形码
     */
    @TableField("barcode_sn")
    private String barcodeSn;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 购买该商品时所选择的属性（包括：颜色、尺码）
     */
    @TableField("goods_attr")
    private String goodsAttr;

    /**
     * 商品的购买数量
     */
    @TableField("goods_number")
    private Integer goodsNumber;

    /**
     * 销售单价(元)来自于平台，或goods_sku_sn_info.market_price
     */
    @TableField("market_price")
    private BigDecimal marketPrice;

    /**
     * 销售总金额(元)来自于平台(是多少就是多少不能计算得出)，或goods_sku_sn_info.market_price
     */
    @TableField("sum_market_price")
    private BigDecimal sumMarketPrice;

    /**
     * 优惠单价(元)
     */
    @TableField("discount")
    private BigDecimal discount;

    /**
     * 优惠总金额(元)
     */
    @TableField("sum_discount")
    private BigDecimal sumDiscount;

    /**
     * 商品的成交单价，退款时用到（注：不乘以数量）
     */
    @TableField("transaction_price")
    private BigDecimal transactionPrice;

    /**
     * 商品的成交总金额
     */
    @TableField("sum_transaction_price")
    private BigDecimal sumTransactionPrice;

    /**
     * 子订单id
     */
    @TableField("oid")
    private String oid;

    /**
     * skuid
     */
    @TableField("skuid")
    private String skuid;

    /**
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * 取消数量:作废数量
     */
    @TableField("cancel_number")
    private Integer cancelNumber;

    /**
     * 申请取消数量:申请作废数量
     */
    @TableField("apply_cancel_number")
    private Integer applyCancelNumber;

    /**
     * 已退货数量
     */
    @TableField("return_number")
    private Integer returnNumber;

    /**
     * 分仓成功数量
     */
    @TableField("ac_key_success_number")
    private Integer acKeySuccessNumber;

    /**
     * 分仓失败数量
     */
    @TableField("ac_key_fail_number")
    private Integer acKeyFailNumber;

    /**
     * 仓库缺货数量
     */
    @TableField("wms_short_pick_number")
    private Integer wmsShortPickNumber;

    /**
     * 渠道ID
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 分仓延迟数量
     */
    @TableField("hold_number")
    private Integer holdNumber;

    /**
     * 通知WMS数量
     */
    @TableField("send_wms_number")
    private Integer sendWmsNumber;

    /**
     * 发货数量
     */
    @TableField("ship_number")
    private Integer shipNumber;

    /**
     * 0没有退款，1有退款
     */
    @TableField("is_refund")
    private Byte isRefund;

    /**
     * 0没有退货，1有退货
     */
    @TableField("is_return")
    private Byte isReturn;

    /**
     * 0没有换货，1有换货
     */
    @TableField("is_change")
    private Byte isChange;

    /**
     * 0非赠品 1 是赠品
     */
    @TableField("is_gift")
    private Byte isGift;

    /**
     * 赠品活动ID
     */
    @TableField("gift_id")
    private Integer giftId;

    /**
     * 0 非套装 1 套装
     */
    @TableField("is_package")
    private Byte isPackage;

    /**
     * 平台单：保存存oid, 手工单换货单：保存系统生成的oid
     */
    @TableField("unique_code")
    private String uniqueCode;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 批量赠品活动ID
     */
    @TableField("bath_gift_id")
    private Integer bathGiftId;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 0为非定制，1为定制
     */
    @TableField("is_customized")
    private Byte isCustomized;

    /**
     * 员工折扣金额
     */
    @TableField("sumoidstaffDiscountValue")
    private BigDecimal sumoidstaffDiscountValue;

    /**
     * 带货达人id
     */
    @TableField("author_id")
    private String authorId;

    /**
     * 带货达人名称
     */
    @TableField("author_name")
    private String authorName;
}
