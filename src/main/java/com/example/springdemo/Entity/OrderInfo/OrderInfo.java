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
 * 订单主信息表
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@Getter
@Setter
@TableName("order_info")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * OMS订单号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 订单类型:1款到发货（交易类型=“fixed(一口价)”）、2货到付款、3虚拟订单、4预售订单（版本三暂不处理）
     */
    @TableField("order_sales_type")
    private Byte orderSalesType;

    /**
     * 用户id, order_user_info.id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 用户昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 订单来源
     */
    @TableField("come_from")
    private String comeFrom;

    /**
     * 订单属性:1平台单,2合单,3换货单,4手工单,
     */
    @TableField("order_type")
    private Byte orderType;

    /**
     * order_type_info.id ， 款到发货，预售订单
     */
    @TableField("type")
    private Integer type;

    /**
     * 渠道id：channel_id.id
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 外部订单号
     */
    @TableField("tid")
    private String tid;

    /**
     * 交易状态：0待匹配赠品，1待审批、2待支付、3审批通过，4审批失败、5待审单、6审单失败、7履约中、8已完成、9已作废
     */
    @TableField("transaction_status")
    private Byte transactionStatus;

    /**
     * 1待通知、2部分通知（N/M件）、3通知完成
     */
    @TableField("notice_status")
    private Byte noticeStatus;

    /**
     * 通知状态备注：2部分通知保存为：N/M件
     */
    @TableField("notice_status_remark")
    private String noticeStatusRemark;

    /**
     * 分仓状态：1待分仓、2分仓中(N/M件)、3分仓成功、4分仓失败
     */
    @TableField("ac_key_status")
    private Byte acKeyStatus;

    /**
     * 分仓状态备注：2分仓中(N/M件)保存为：N/M件
     */
    @TableField("ac_key_status_remark")
    private String acKeyStatusRemark;

    /**
     * 配送费用
     */
    @TableField("shipping_pay_sum")
    private BigDecimal shippingPaySum;

    /**
     * 发货状态：1待发货、2发货中(N/M件)、3已发货
     */
    @TableField("shipping_status")
    private Byte shippingStatus;

    /**
     * 发货状态：2发货中(N/M件)保存为：N/M件
     */
    @TableField("shipping_status_remark")
    private String shippingStatusRemark;

    /**
     * 支付方式
     */
    @TableField("pay_name")
    private String payName;

    /**
     * 付款状态：1待付款、2已付款, 3交易失败
     */
    @TableField("pay_status")
    private Byte payStatus;

    /**
     * 订单属性配置
     */
    @TableField("tid_remark")
    private String tidRemark;

    /**
     * 问题单状态 （1 否  2 是）
     */
    @TableField("question_status")
    private Byte questionStatus;

    /**
     * 购买总数
     */
    @TableField("order_goods_count")
    private Integer orderGoodsCount;

    /**
     * 实际支付总额
     */
    @TableField("order_actually_pay_sum")
    private BigDecimal orderActuallyPaySum;

    /**
     * 需要支付总额
     */
    @TableField("order_expected_pay_sum")
    private BigDecimal orderExpectedPaySum;

    /**
     * 其他优惠金额
     */
    @TableField("order_other_discount_sum")
    private BigDecimal orderOtherDiscountSum;

    /**
     * tid级别的优惠金额
     */
    @TableField("order_discount_sum")
    private BigDecimal orderDiscountSum;

    /**
     * 商品总金额
     */
    @TableField("goods_pay_sum")
    private BigDecimal goodsPaySum;

    /**
     * 在平台的下单时间
     */
    @TableField("channel_add_time")
    private Date channelAddTime;

    /**
     * 付款时间
     */
    @TableField("pay_time")
    private Date payTime;

    /**
     * 合并来源: order_sn 用英文逗号分隔开
     */
    @TableField("merge_from")
    private String mergeFrom;

    /**
     * 1为非合单，2为合单
     */
    @TableField("merge_is")
    private Byte mergeIs;

    /**
     * 是否存在换货单:0,否;1,是
     */
    @TableField("is_have_change")
    private Byte isHaveChange;

    /**
     * 换货来源单: order_sn
     */
    @TableField("change_from")
    private Long changeFrom;

    /**
     * 卖家留言
     */
    @TableField("seller_msg")
    private String sellerMsg;

    /**
     * 买家留言
     */
    @TableField("buyer_msg")
    private String buyerMsg;

    /**
     * 用户code , -1代表系统
     */
    @TableField("create_user_code")
    private String createUserCode;

    /**
     * 创建时间:系统制单时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * 审批状态 1：未审批 2已审批
     */
    @TableField("review_status")
    private Boolean reviewStatus;

    /**
     * 审批时间
     */
    @TableField("review_time")
    private Date reviewTime;

    /**
     * 是否已审单 1未审单，2已审单
     */
    @TableField("confirm_status")
    private Boolean confirmStatus;

    /**
     * 审单时间
     */
    @TableField("confirm_time")
    private Date confirmTime;

    /**
     * 作废状态 1未作废，2已作废
     */
    @TableField("cancel_status")
    private Byte cancelStatus;

    /**
     * 取消时间（tid维度）
     */
    @TableField("cancel_time")
    private Date cancelTime;

    /**
     * 完成时间：全部出库后的时间
     */
    @TableField("finish_time")
    private Date finishTime;

    /**
     * 买家指定快递，主库sys_shipping_list.shipping_code
     */
    @TableField("shipping_code")
    private String shippingCode;

    /**
     * 分仓成功数量
     */
    @TableField("ac_key_success_number")
    private Integer acKeySuccessNumber;

    /**
     * 通知WMS数量
     */
    @TableField("send_wms_number")
    private Integer sendWmsNumber;

    /**
     * 1符合，2不符合(人工审核)
     */
    @TableField("is_auto_confirm")
    private Byte isAutoConfirm;

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
     * 0为非定制，1为定制
     */
    @TableField("is_customized")
    private Byte isCustomized;

    /**
     * 0不缺货，1整单缺货，2部分缺货
     */
    @TableField("is_oos")
    private Boolean isOos;

    /**
     * order_after_change_info.id， 售后散的换货单单号
     */
    @TableField("order_after_change_info_id")
    private Long orderAfterChangeInfoId;

    /**
     * 0为非京东父订单（正常给WMS发货），1京东父订单(一定不能给WMS发货)
     */
    @TableField("is_die")
    private Byte isDie;

    /**
     * 0为非京东拆单，1京东拆单
     */
    @TableField("jd_is_son")
    private Byte jdIsSon;

    /**
     * 0非唯品单，1门店模式，2省仓模式
     */
    @TableField("is_vip")
    private Boolean isVip;

    /**
     * 唯品合单码
     */
    @TableField("vip_merged_code")
    private String vipMergedCode;

    /**
     * 员工卡号
     */
    @TableField("staffCardNumber")
    private String staffCardNumber;

    /**
     * 员工折扣金额
     */
    @TableField("sumstaffDiscountValue")
    private BigDecimal sumstaffDiscountValue;

    /**
     * 1即时订单，2寻仓单，3正式单
     */
    @TableField("vip_order_status")
    private Byte vipOrderStatus;

    /**
     * 0未合过，1已合过
     */
    @TableField("vip_merged_fix")
    private Byte vipMergedFix;

    /**
     * 指定仓库（保存虚仓编码）
     */
    @TableField("store_code_json")
    private String storeCodeJson;

    /**
     * 指定快递单号
     */
    @TableField("appoint_invoice_sn")
    private String appointInvoiceSn;

    /**
     * 正式订单更新时间
     */
    @TableField("official_order_time")
    private Date officialOrderTime;

    /**
     * 外部指定仓库编码
     */
    @TableField("delivery_warehouse")
    private String deliveryWarehouse;

    /**
     * 品牌编码
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 发货回调订单号
     */
    @TableField("shipping_order_id")
    private String shippingOrderId;
}
