package com.example.springdemo.Entity.TChannelOrderLog;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 渠道订单流水表(日志表)
 * </p>
 *
 * @author 月光光
 * @since 2023-05-30
 */
@Getter
@Setter
@TableName("t_channel_order_log")
public class TChannelOrderLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 渠道id
     */
    @TableField("channel_id")
    private Integer channelId;

    /**
     * 外部订单号
     */
    @TableField("tid")
    private String tid;

    /**
     * 流水类型, 1订单正向
     */
    @TableField("type")
    private Byte type;

    /**
     * 当前版本号
     */
    @Version
    @TableField("current_version")
    private Integer currentVersion;

    /**
     * 接收到的数据
     */
    @TableField("content")
    private String content;

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
     * 外部平台更新时间，用来做current_version的判断
     */
    @TableField("outer_update_time")
    private Date outerUpdateTime;

    /**
     * 公司编码
     */
    @TableField("company_code")
    private String companyCode;
}
