package com.example.springdemo.Entity.OrderInfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 定制商品的定制内容
 * </p>
 *
 * @author 月光光
 * @since 2023-06-02
 */
@Getter
@Setter
@TableName("order_goods_customized")
public class OrderGoodsCustomized implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * order_goods.id
     */
    @TableField("order_goods_id")
    private Long orderGoodsId;

    /**
     * 定制化内容（原内容）
     */
    @TableField("customized_text")
    private String customizedText;

    /**
     * [{"content": "定制内容", "key": "定制内容名","type": "默为0，1代表图片"}]
     */
    @TableField("customized_detail")
    private String customizedDetail;

    /**
     * sku_sn
     */
    @TableField("sku_sn")
    private String skuSn;

    /**
     * oid
     */
    @TableField("oid")
    private String oid;

    /**
     * OMS订单号
     */
    @TableField("order_sn")
    private String orderSn;
}
