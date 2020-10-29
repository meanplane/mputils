package com.mp.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * Author: Xiaoer
 * Date: 2020-10-24
 */
public class TestHash {

    public static void main(String[] args) {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        CustomerRealm realm = new CustomerRealm();

        // 设置realm使用hash凭证选择器


        securityManager.setRealm(realm);

        SecurityUtils.setSecurityManager(securityManager);


        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("tom", "123");

        subject.login(token);

    }
}
