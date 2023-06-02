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
 * 商品颜色表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-03
 */
@Getter
@Setter
@TableName("goods_color")
public class GoodsColor implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 颜色
     */
    @TableField("color_name")
    private String colorName;

    /**
     * 品牌code
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 外部颜色编码
     */
    @TableField("out_color_code")
    private String outColorCode;

    /**
     * 公司code
     */
    @TableField("company_code")
    private String companyCode;

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
}
