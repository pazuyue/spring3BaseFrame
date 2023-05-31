package com.example.springdemo.Entity.TChannel;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author 月光光
 * @since 2023-05-29
 */
@Data
@TableName("t_channel")
public class TChannel implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int TM = 1; //天猫渠道

    /**
     * 渠道id
     */
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Integer channelId;

    /**
     * 渠道名称
     */
    @TableField("channel_name")
    private String channelName;

    /**
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * 店铺类型相关信息
     */
    @TableField("shop_type")
    private String shopType;

    /**
     * 订单来源, channel_type_info.type
     */
    @TableField("channel_type")
    private String channelType;

    /**
     * 是否启用 1-开启,2-禁用
     */
    @TableField("enabled")
    private Byte enabled;

    /**
     * 对接平台, 1：需对接，2：无需对接
     */
    @TableField("to_channel_enabled")
    private Byte toChannelEnabled;

    /**
     * 是否同步库存 1-开启,2-关闭
     */
    @TableField("sync_enabled")
    private Byte syncEnabled;

    /**
     * 常态合作编码(VIP OXO用到)
     */
    @TableField("cooperation_no")
    private String cooperationNo;

    /**
     * 供应商编码
     */
    @TableField("vendor_code")
    private String vendorCode;

    /**
     * 经营模式：1直营,2加盟
     */
    @TableField("m_model_type")
    private Byte mModelType;

    /**
     * 经销商dealer_info.dealer_code
     */
    @TableField("dealer_code")
    private String dealerCode;

    /**
     * 服务到期时间
     */
    @TableField("sevice_end_time")
    private Date seviceEndTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 品牌名--抛丢不能再使用
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 分仓规则id
     */
    @TableField("order_wms_rule_id")
    private Long orderWmsRuleId;

    /**
     * 自动审单规则:id
     */
    @TableField("orderconfirm_rule_id")
    private Long orderconfirmRuleId;

    /**
     * 平台配送规则::order_channel_shipping.id
     */
    @TableField("shipping_rule_id")
    private Long shippingRuleId;

    /**
     * 换货配送规则
     */
    @TableField("change_rule_id")
    private Long changeRuleId;

    /**
     * 是否开启自动审单 1开启，2关闭
     */
    @TableField("orderconfirm_enable")
    private Byte orderconfirmEnable;

    /**
     * 自动分仓最迟分仓时间，分钟为单位
     */
    @TableField("ac_hold_time")
    private Long acHoldTime;

    /**
     * 自动审单hold单时间,分钟为单位
     */
    @TableField("orderconfirm_hold_time")
    private Long orderconfirmHoldTime;

    /**
     * 更新人的编码
     */
    @TableField("update_user_code")
    private String updateUserCode;

    /**
     * 顺丰是否解密推送，1开启，2关闭
     */
    @TableField("sf_decrypt")
    private Byte sfDecrypt;

    /**
     * wms_simulation_store_info.wms_simulation_code:退货回库的仓库
     */
    @TableField("return_store_code")
    private String returnStoreCode;

    /**
     * 退货退款单-商家初审自动同意退货:1关闭，2开启
     */
    @TableField("open_auto_refund")
    private Byte openAutoRefund;

    /**
     * 等待平台回调售中换货结果时间:单位：分钟
     */
    @TableField("wait_channel_change_time")
    private Integer waitChannelChangeTime;

    /**
     * 买家留言是否需要设置问题单, 0 不需要 1需要
     */
    @TableField("buyer_msg_question_enabled")
    private Byte buyerMsgQuestionEnabled;

    /**
     * 卖家留言是否需要设置问题单, 0 不需要 1需要
     */
    @TableField("seller_msg_question_enabled")
    private Byte sellerMsgQuestionEnabled;

    /**
     * 是否开启只拉取“客服已同意”的换货单: 1=是，2=否
     */
    @TableField("exchange_wait_process_status")
    private Boolean exchangeWaitProcessStatus;

    /**
     * 平台的商家ID
     */
    @TableField("businessid")
    private String businessid;

    /**
     * 订单仓内结果回传平台发货，见taobao.qimen.orderprocess.report接口
     */
    @TableField("process_advance")
    private String processAdvance;

    /**
     * 是否开启售后换货自动初审: 0不开启，1开启
     */
    @TableField("init_change_auto_confirm_enable")
    private Byte initChangeAutoConfirmEnable;

    /**
     * 售后换货初审规则:init_change_auto_confirm_rule.id
     */
    @TableField("init_change_auto_confirm_rule_id")
    private Long initChangeAutoConfirmRuleId;

    /**
     * 1：jitx门店模式，2:jitx省仓模式，0:普通jitx模式
     */
    @TableField("vip_send_type")
    private Byte vipSendType;

    /**
     * 授权到期后的提醒信息
     */
    @TableField("notice_msg")
    private String noticeMsg;

    /**
     * 0普通店，1唯品的店
     */
    @TableField("is_vip")
    private Boolean isVip;

    /**
     * 受权到期后提醒邮箱
     */
    @TableField("notice_mail")
    private String noticeMail;

    /**
     * 采购档期
     */
    @TableField("vip_cg_data")
    private String vipCgData;

    /**
     * 1正常，2HM官网类型 （回调平台的接口类型）	3ECCO官网类型 （回调平台的接口类型）
     */
    @TableField("interface_type")
    private Byte interfaceType;

    /**
     * 生成退货单维度：0.售后单维度；1.买家寄回快递维度
     */
    @TableField("create_return_type")
    private Boolean createReturnType;

    /**
     * 0 默认青木OMS分仓 1 外部系统分仓
     */
    @TableField("warehouse_separation_system")
    private Byte warehouseSeparationSystem;

    /**
     * ofs分仓规则id,表ofs_order_wms_rule主键id
     */
    @TableField("ofs_order_wms_rule_id")
    private Integer ofsOrderWmsRuleId;

    /**
     * 1不加密，2加密
     */
    @TableField("order_encrypt")
    private Byte orderEncrypt;

    /**
     * 批量同步sku的个数，默认1
     */
    @TableField("update_goods_stock_num")
    private Integer updateGoodsStockNum;

    /**
     * 官网/小程序初审超时时间
     */
    @TableField("wechat_refund_out_time")
    private Integer wechatRefundOutTime;
}
