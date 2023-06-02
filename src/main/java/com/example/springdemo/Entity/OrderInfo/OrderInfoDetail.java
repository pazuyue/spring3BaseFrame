package com.example.springdemo.Entity.OrderInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单详情表（地址）
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_info_detail")
public class OrderInfoDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("order_sn")
    private String orderSn;

    /**
     * 外部订单号
     */
    @TableField("tid")
    private String tid;

    /**
     * 收货人的姓名，用户页面填写，默认取值于表order_user_address
     */
    @TableField("consignee")
    private String consignee;

    /**
     * 收货人的国家
     */
    @TableField("country")
    private String country;

    /**
     * 省
     */
    @TableField("province")
    private String province;

    /**
     * 市
     */
    @TableField("city")
    private String city;

    /**
     * 区
     */
    @TableField("district")
    private String district;

    /**
     * 镇/街道
     */
    @TableField("town")
    private String town;

    /**
     * 收货人的详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 收货人的邮政编码
     */
    @TableField("zipcode")
    private String zipcode;

    /**
     * 收货人的电话号码
     */
    @TableField("tel")
    private String tel;

    /**
     * 收货人的手机号码
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 收货人的电子邮件
     */
    @TableField("email")
    private String email;

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
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;
}
