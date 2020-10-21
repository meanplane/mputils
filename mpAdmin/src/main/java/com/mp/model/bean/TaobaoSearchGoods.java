package com.mp.model.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("taobao_search_goods")
public class TaobaoSearchGoods implements Serializable {
    private Long id;
    private String img;
    private String name;
    private BigDecimal price;
}
