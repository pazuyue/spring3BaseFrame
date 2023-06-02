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
 * 订单商品明细表:ol级
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_goods_order_line")
public class OrderGoodsOrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * order_goods表的id
     */
    @TableField("order_goods_id")
    private Long orderGoodsId;

    /**
     * 订单号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * sku_sn
     */
    @TableField("sku_sn")
    private String skuSn;

    /**
     * 子订单id
     */
    @TableField("oid")
    private String oid;

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
     * order line 编码
     */
    @TableField("order_line_no")
    private String orderLineNo;

    /**
     * 1待分仓、3分仓成功、6分仓失败、8分仓延迟、10已通知、13已作废、14已发货、16已退货
     */
    @TableField("status")
    private Byte status;

    /**
     * 分仓组的key
     */
    @TableField("ac_key")
    private String acKey;

    /**
     * 商品数量
     */
    @TableField("goods_number")
    private Integer goodsNumber;

    /**
     * 运单号
     */
    @TableField("invoice_sn")
    private String invoiceSn;

    /**
     * 出库单据编号
     */
    @TableField("ticket_sn_xs")
    private String ticketSnXs;

    /**
     * 真实出库的仓.虚仓库编码
     */
    @TableField("real_stock_code")
    private String realStockCode;

    /**
     * wms_inventory_batch.batch_code, 采购批次 ：,实际出库的批次
     */
    @TableField("batch_code")
    private String batchCode;

    /**
     * 分配出库的虚仓（多个则用英文逗号分隔）
     */
    @TableField("stock_code")
    private String stockCode;

    /**
     * 系统匹配的快递：用户选择的配送方式代码，取值表主库中sys_shipping_list 当中的 code
     */
    @TableField("shipping_code")
    private String shippingCode;

    /**
     * 仓库发货快递，取值表主库中sys_shipping_list 当中的 code
     */
    @TableField("real_shipping_code")
    private String realShippingCode;

    /**
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * 1没有发货回调平台，2有发货回调平台
     */
    @TableField("is_callback")
    private Byte isCallback;

    /**
     * 发货回调平台时间
     */
    @TableField("is_callback_time")
    private Date isCallbackTime;

    /**
     * 1无仓库缺货，2仓库缺货
     */
    @TableField("is_wms_short_pick")
    private Byte isWmsShortPick;

    /**
     * 商品的成交总金额
     */
    @TableField("transaction_price")
    private BigDecimal transactionPrice;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * order_after_refund_info.id:仅退款才打
     */
    @TableField("order_after_refund_info_id")
    private Long orderAfterRefundInfoId;

    /**
     * order_after_return_info.id：仅是申请退货退款
     */
    @TableField("order_after_return_info_id")
    private Long orderAfterReturnInfoId;

    /**
     * order_after_change_info.id：售中售后的换货都打
     */
    @TableField("order_after_change_info_id")
    private Long orderAfterChangeInfoId;

    /**
     * order_after_return_th.id
     */
    @TableField("order_after_return_th_id")
    private Long orderAfterReturnThId;

    /**
     * 1退款，2退货，3换货
     */
    @TableField("after_status")
    private Byte afterStatus;

    /**
     * 分仓失败的原因
     */
    @TableField("fail_ac_reason")
    private String failAcReason;

    /**
     * 是否赠品 1是 0否
     */
    @TableField("is_gift")
    private Byte isGift;

    /**
     * 0没有入库，1入库正品，2入库次品
     */
    @TableField("th_zcp")
    private Byte thZcp;

    /**
     * 0未检测 1 已检测  用于合单对照寻仓单
     */
    @TableField("ofs_check")
    private Byte ofsCheck;
}
