package com.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.model.bean.AliGoodsInfo;
import com.mp.model.bean.AliSearchGoods;
import com.mp.model.mapper.AliGoodsMapper;
import com.mp.model.mapper.AliSearchMapper;
import com.mp.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ali")
public class AliController {
    @Autowired
    private AliGoodsMapper aliGoodsMapper;

    @Autowired
    private AliSearchMapper aliSearchMapper;

    // 上传找货信息
    @RequestMapping(value = "/goods/upload", method = RequestMethod.POST)
    public R uploadGoodsInfo(@RequestBody List<AliGoodsInfo> goodsInfos) {

        if (goodsInfos != null && goodsInfos.size() > 0) {
            AliGoodsInfo goodsInfo = goodsInfos.get(0);

            AliSearchGoods goods = new AliSearchGoods();
            goods.setImg(goodsInfo.getImg());
            goods.setName(goodsInfo.getName());

            // 计算平均价格
            //求平均值
            BigDecimal average = goodsInfos.stream().map(AliGoodsInfo::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(goodsInfos.size()), 2, BigDecimal.ROUND_HALF_UP);
            goods.setPrice(average);

            aliSearchMapper.insert(goods);

            goodsInfos.forEach(g -> {
                g.setGoodsId(goods.getId());
                System.out.println(g);
                aliGoodsMapper.insert(g);
            });

        }
        return R.ok();
    }

    @RequestMapping(value = "/goods/history/list", method = RequestMethod.GET)
    public R getHistoryList() {
        List<AliSearchGoods> goods = aliSearchMapper.selectList(new QueryWrapper<AliSearchGoods>().orderByDesc("id"));
        System.out.println(goods);
        return R.ok(goods);
    }

    @RequestMapping(value = "/goods/history/delete", method = RequestMethod.GET)
    public R deleteHistoryGoods(@RequestParam("goodsId") Long goodsId) {
        aliSearchMapper.deleteById(goodsId);
        aliGoodsMapper.delete(new QueryWrapper<AliGoodsInfo>().eq("goodsId", goodsId));
        return R.ok();
    }

    @RequestMapping(value = "/goods/info/list", method = RequestMethod.GET)
    public R getGoodsInfoList(@RequestParam("goodsId") Long goodsId) {
        List<AliGoodsInfo> goods = aliGoodsMapper
                .selectList(new QueryWrapper<AliGoodsInfo>().eq("goodsId", goodsId).orderByDesc("id"));
        System.out.println(goods);
        return R.ok(goods);
    }
}
