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
 * 
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_user_info")
public class OrderUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * t_channel.id
     */
    @TableField("t_channel_id")
    private Long tChannelId;

    /**
     * 渠道用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 手机
     */
    @TableField("mobile")
    private String mobile;

    /**
     * 状态 1正常 2黑名单
     */
    @TableField("status")
    private Integer status;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 真实姓名
     */
    @TableField("real_name")
    private String realName;

    /**
     * 性别 1男  0女  2保密
     */
    @TableField("sex")
    private Short sex;

    /**
     * 生日
     */
    @TableField("birth_date")
    private Date birthDate;

    /**
     * 出生年
     */
    @TableField("birth_year")
    private Integer birthYear;

    /**
     * 出生月
     */
    @TableField("birth_month")
    private Byte birthMonth;

    /**
     * 出生日
     */
    @TableField("birth_day")
    private Byte birthDay;

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
    @TableField("company_code")
    private String companyCode;
}
