package com.mp.model.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("taobao_goods_info")
public class TaobaoGoodsInfo implements Serializable {

    @TableId
    private Long id;

    // 商品名
    private String name;

    // 价格
    private BigDecimal price;

    // 付款人数
    private Integer dealCount;

    // 商品链接
    private String goodsUrl;

    // 图片
    private String img;

    // 商铺名
    private String shopName;

    // 商铺url
    private String shopUrl;

    // 地域
    private String location;

    // 商品id(淘宝)
    private Long goodsId;
}
