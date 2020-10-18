package com.mp.controller;

import com.mp.model.bean.AliGoodsInfo;
import com.mp.utils.R;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ali")
public class AliController {

    // 上传找货信息
    @RequestMapping("/goods/upload")
    public R uploadGoodsInfo(@RequestBody List<AliGoodsInfo> goodsInfos) {
        System.out.println("##################################");
        for (AliGoodsInfo goodsInfo : goodsInfos) {
            String goodsUrl = goodsInfo.getGoodsUrl();

            System.out.println(goodsInfo);
        }
        System.out.println("##################################");
        return R.ok();
    }
}
