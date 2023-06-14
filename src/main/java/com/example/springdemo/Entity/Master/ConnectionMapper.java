package com.example.springdemo.Entity.Master;

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
 * 连接映射表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-14
 */
@Getter
@Setter
@TableName("connection_mapper")
public class ConnectionMapper implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 公司code
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * db连接名
     */
    @TableField("connection_name")
    private String connectionName;

    /**
     * 配置json
     */
    @TableField("config_json")
    private String configJson;

    /**
     * 添加时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private Date modifyTime;

    /**
     * 1生效，2不生效
     */
    @TableField("status")
    private Byte status;
}
