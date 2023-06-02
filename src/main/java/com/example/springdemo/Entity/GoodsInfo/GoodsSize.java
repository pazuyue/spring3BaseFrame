package com.example.springdemo.Entity.GoodsInfo;

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
 * 商品尺码表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-03
 */
@Getter
@Setter
@TableName("goods_size")
public class GoodsSize implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 尺码名称
     */
    @TableField("size_name")
    private String sizeName;

    /**
     * 关联品牌
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 公司code
     */
    @TableField("company_code")
    private String companyCode;

    /**
     * 外部尺码编码
     */
    @TableField("out_size_code")
    private String outSizeCode;

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
}
