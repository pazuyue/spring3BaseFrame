package com.example.springdemo.Entity.GoodsInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 产品信息表
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("goods_sku_sn_info")
public class GoodsSkuSnInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * sku
     */
    @TableField("sku_sn")
    private String skuSn;

    /**
     * 货号
     */
    @TableField("goods_sn")
    private String goodsSn;

    /**
     * 条形码
     */
    @TableField("barcode_sn")
    private String barcodeSn;

    /**
     * 商品名称
     */
    @TableField("goods_name")
    private String goodsName;

    /**
     * 品牌编码
     */
    @TableField("brand_code")
    private String brandCode;

    /**
     * 品牌名
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 类目code
     */
    @TableField("category_code")
    private Integer categoryCode;

    /**
     * 颜色编码
     */
    @TableField("color_code")
    private Integer colorCode;

    /**
     * 尺码,goods_size.id
     */
    @TableField("size_code")
    private Integer sizeCode;

    /**
     * 市场价（吊牌价）
     */
    @TableField("market_price")
    private BigDecimal marketPrice;

    /**
     * 需要有效期管理,0不需要，1需要
     */
    @TableField("is_need_effect")
    private Byte isNeedEffect;

    /**
     * 有效期
     */
    @TableField("validity")
    private String validity;

    /**
     * 商品描述
     */
    @TableField("goods_desc")
    private String goodsDesc;

    /**
     * 是否福袋,0 不是福袋，1是福袋
     */
    @TableField("is_fd")
    private Byte isFd;

    /**
     * 0为非赠口，1为赠品
     */
    @TableField("is_gift")
    private Byte isGift;
    @TableField("deleted_at")
    private Date deletedAt;

    /**
     * 公司编码
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

    /**
     * 0正常商品，1套装商品
     */
    @TableField("is_package")
    private Byte isPackage;

    /**
     * 创建套装的用户编码
     */
    @TableField("create_package_user")
    private String createPackageUser;

    /**
     * 商品描述
     */
    @TableField("description")
    private String description;
}
