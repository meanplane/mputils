package com.mp.test;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * Author: Xiaoer
 * Date: 2020-10-24
 */
public class TestHash {

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash();
        md5Hash.setBytes("123".getBytes());

        System.out.println(md5Hash.toHex());
        System.out.println(md5Hash.toBase64());
        System.out.println(md5Hash.toString());

    }
}
