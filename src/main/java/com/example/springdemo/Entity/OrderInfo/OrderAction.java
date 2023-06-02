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
 * 对订单操作日志表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_action")
public class OrderAction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "action_id", type = IdType.AUTO)
    private Long actionId;

    /**
     * 订单号
     */
    @TableField("order_sn")
    private String orderSn;

    /**
     * 操作人用户名
     */
    @TableField("action_user")
    private String actionUser;

    /**
     * 订单状态
     */
    @TableField("order_status")
    private String orderStatus;

    /**
     * 操作备注
     */
    @TableField("action_note")
    private String actionNote;

    /**
     * 操作时间
     */
    @TableField("log_time")
    private Date logTime;

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
