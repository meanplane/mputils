package com.mp.model.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.model.bean.AliGoodsInfo;
import com.mp.model.bean.AliSearchGoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AliSearchMapper extends BaseMapper<AliSearchGoods> {
}
