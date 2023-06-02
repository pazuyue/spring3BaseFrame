package com.example.springdemo.Entity.TChannel;

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
 * 渠道规则表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-01
 */
@Getter
@Setter
@TableName("t_channel_rules")
public class TChannelRules implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的渠道id
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 拉取订单时间
     */
    @TableField("pull_order_time")
    private Date pullOrderTime;

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
     * 修改时间
     */
    @TableField("modify_time_dc")
    private Date modifyTimeDc;
}
