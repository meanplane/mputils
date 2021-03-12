package com.mp.test;

import org.ansj.domain.Result;
import org.ansj.splitWord.analysis.DicAnalysis;

/**
 * Author: Xiaoer
 * Date: 2020-10-24
 */
public class TestHash {

    public static void main(String[] args) throws Exception {
        String str = "HXXXXS恶搞康康包轻奢潮牌真皮小号豆腐包女包单肩斜挎包";
        Result ret = DicAnalysis.parse(str);
        System.out.println("=============\n\n");
//        System.out.println("ToAnalysis "+ToAnalysis.parse(str));
        System.out.println("DicAnalysis "+ret);
//        System.out.println("IndexAnalysis "+IndexAnalysis.parse(str));
//        System.out.println("NlpAnalysis" +NlpAnalysis.parse(str));
        System.out.println("\n\n=============");


    }
}
