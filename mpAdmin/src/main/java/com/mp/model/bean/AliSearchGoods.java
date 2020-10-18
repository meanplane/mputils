package com.mp.model.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("ali_search_goods")
public class AliSearchGoods implements Serializable {
    private Long id;
    private String img;
    private String name;
    private BigDecimal price;
}
