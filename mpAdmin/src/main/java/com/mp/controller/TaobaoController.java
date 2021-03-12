package com.mp.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.model.bean.AliGoodsInfo;
import com.mp.model.bean.AliSearchGoods;
import com.mp.model.bean.TaobaoGoodsInfo;
import com.mp.model.bean.TaobaoSearchGoods;
import com.mp.model.bean.vo.TitleWord;
import com.mp.model.bean.vo.WordCount;
import com.mp.model.mapper.AliGoodsMapper;
import com.mp.model.mapper.AliSearchMapper;
import com.mp.model.mapper.TaobaoGoodsMapper;
import com.mp.model.mapper.TaobaoSearchMapper;
import com.mp.utils.R;
import org.ansj.domain.Result;
import org.ansj.library.DicLibrary;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/taobao")
public class TaobaoController {
    @Autowired
    private TaobaoGoodsMapper taobaoGoodsMapper;

    @Autowired
    private TaobaoSearchMapper taobaoSearchMapper;

    // 上传找货信息
    @RequestMapping(value = "/goods/upload", method = RequestMethod.POST)
    public R uploadGoodsInfo(@RequestBody List<TaobaoGoodsInfo> goodsInfos) {

        if (goodsInfos != null && goodsInfos.size() > 0) {
            TaobaoGoodsInfo goodsInfo = goodsInfos.get(0);

            TaobaoSearchGoods goods = new TaobaoSearchGoods();

            goods.setImg(goodsInfo.getImg());
            goods.setName(goodsInfo.getName());

            // 计算平均价格
            //求平均值
            BigDecimal average = goodsInfos.stream().map(TaobaoGoodsInfo::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add)
                    .divide(BigDecimal.valueOf(goodsInfos.size()), 2, BigDecimal.ROUND_HALF_UP);
            goods.setPrice(average);

            taobaoSearchMapper.insert(goods);

            goodsInfos.forEach(g -> {
                g.setGoodsId(goods.getId());
                System.out.println(g);
                taobaoGoodsMapper.insert(g);
            });

        }
        System.out.println("############################");

        for (TaobaoGoodsInfo goodsInfo : goodsInfos) {
            System.out.println(goodsInfo);
        }

        System.out.println("############################");
        return R.ok();
    }

    @RequestMapping(value = "/goods/history/list", method = RequestMethod.GET)
    public R getHistoryList() {
        List<TaobaoSearchGoods> goods = taobaoSearchMapper.selectList(new QueryWrapper<TaobaoSearchGoods>().orderByDesc("id"));
        return R.ok(goods);
    }

    @RequestMapping(value = "/title/words", method = RequestMethod.GET)
    public R getTitleWords(@RequestParam("goodsId") Long goodsId) {
        List<TaobaoGoodsInfo> goods = taobaoGoodsMapper
                .selectList(new QueryWrapper<TaobaoGoodsInfo>().eq("goodsId", goodsId).orderByDesc("id"));
        HashMap<String, Object> map = new HashMap<>();

        // 原始分词信息
        ArrayList<TitleWord> titleWords = new ArrayList<>();

        // 统计分词数量
        HashMap<String, Integer> wordsMap = new HashMap<>();

        for (TaobaoGoodsInfo good : goods) {
            TitleWord titleWord = new TitleWord();
            Result ret = DicAnalysis.parse(good.getName());

            titleWord.setName(good.getName());
            String words = ret.toStringWithOutNature();
            titleWord.setWord(words);
            titleWords.add(titleWord);

            //统计分词
            String[] wordsList = StringUtils.split(words, ",");
            for (String str : wordsList) {
                Integer count = wordsMap.get(str);
                if (count == null) {
                    count = 0;
                }

                wordsMap.put(str, count + 1);
            }
        }
        map.put("titleWords", titleWords);

        ArrayList<WordCount> list = new ArrayList<>();
        for (String s : wordsMap.keySet()) {
            WordCount wordCount = new WordCount();
            wordCount.setName(s);
            wordCount.setCount(wordsMap.get(s));

            list.add(wordCount);
        }

        map.put("wordsCount", list);


        return R.ok(map);
    }


    @RequestMapping(value = "/goods/history/delete", method = RequestMethod.GET)
    public R deleteHistoryGoods(@RequestParam("goodsId") Long goodsId) {
        taobaoSearchMapper.deleteById(goodsId);
        taobaoGoodsMapper.delete(new QueryWrapper<TaobaoGoodsInfo>().eq("goodsId", goodsId));
        return R.ok();
    }

    @RequestMapping(value = "/goods/info/list", method = RequestMethod.GET)
    public R getGoodsInfoList(@RequestParam("goodsId") Long goodsId) {
        List<TaobaoGoodsInfo> goods = taobaoGoodsMapper
                .selectList(new QueryWrapper<TaobaoGoodsInfo>().eq("goodsId", goodsId).orderByDesc("id"));
        System.out.println(goods);
        return R.ok(goods);
    }
}
