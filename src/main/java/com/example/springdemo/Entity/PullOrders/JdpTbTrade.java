package com.example.springdemo.Entity.PullOrders;

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
 * @since 2023-05-31
 */
@Getter
@Setter
@TableName("jdp_tb_trade")
public class JdpTbTrade implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("tid")
    private Long tid;
    @TableField("status")
    private String status;
    @TableField("type")
    private String type;
    @TableField("seller_nick")
    private String sellerNick;
    @TableField("buyer_nick")
    private String buyerNick;
    @TableField("created")
    private Date created;
    @TableField("modified")
    private Date modified;
    @TableField("jdp_hashcode")
    private String jdpHashcode;
    @TableField("jdp_response")
    private String jdpResponse;
    @TableField("jdp_created")
    private Date jdpCreated;
    @TableField("jdp_modified")
    private Date jdpModified;
}
