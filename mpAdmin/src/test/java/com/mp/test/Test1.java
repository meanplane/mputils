package com.mp.test;

import com.mp.Application;
import com.mp.model.bean.TaobaoGoodsInfo;
import com.mp.model.mapper.TaobaoGoodsMapper;
import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.DicAnalysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * Author: Xiaoer
 * Date: 2021-03-12
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class Test1 {
    @Autowired
    private TaobaoGoodsMapper taobaoGoodsMapper;

    @Test
    public void testTaobao(){
        HashMap<String, Integer> wordsMap = new HashMap<>();
        wordsMap.put("xx",2);
        Integer ss = wordsMap.get("ss");
        System.out.println(ss);
    }
}
