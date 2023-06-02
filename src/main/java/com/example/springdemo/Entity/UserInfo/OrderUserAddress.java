package com.example.springdemo.Entity.UserInfo;

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
 * 用户地址表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_user_address")
public class OrderUserAddress implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户表中的流水号,order_user_info.id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 收货人的名字
     */
    @TableField("consignee")
    private String consignee;
    @TableField("email")
    private String email;

    /**
     * 国家
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
     * 收货人所在的乡镇
     */
    @TableField("town")
    private String town;

    /**
     * 收货详细地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮政编码
     */
    @TableField("zipcode")
    private String zipcode;

    /**
     * 电话号码
     */
    @TableField("tel")
    private String tel;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 首选地址
     */
    @TableField("firstaddress")
    private Boolean firstaddress;

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
    @TableField("company_code")
    private String companyCode;
}
