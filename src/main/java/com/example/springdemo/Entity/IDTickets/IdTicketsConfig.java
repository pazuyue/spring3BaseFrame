package com.example.springdemo.Entity.IDTickets;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 编码生成表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-07
 */
@Getter
@Setter
@TableName("id_tickets_config")
public class IdTicketsConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("seq")
    private Integer seq;

    /**
     * 单据类型
     */
    @TableField("type")
    private String type;
}
