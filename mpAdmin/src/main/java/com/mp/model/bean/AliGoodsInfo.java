package com.mp.model.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("ali_goods_info")
public class AliGoodsInfo implements Serializable {

    @TableId
    private Long id;

    // 价格
    private BigDecimal price;

    // 成交金额
    private BigDecimal deal;

    // 回购率
    private BigDecimal rebuy;

    // 商品链接
    private String goodsUrl;

    // 商品名
    private String name;

    // 商品id(阿里)
    private Long goodsId;

    // 图片
    private String img;

    // 公司名称
    private String corp;
}
