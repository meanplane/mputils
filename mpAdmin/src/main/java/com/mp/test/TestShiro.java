package com.mp.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * Author: Xiaoer
 * Date: 2020-10-24
 */
public class TestShiro {
    public static void main(String[] args) {
        // 创建安全管理器
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

        // 给安全管理器设置 realm
//        defaultSecurityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        defaultSecurityManager.setRealm(new CustomerRealm());

        // SecurityUtils 全局安全工具类 设置 安全管理器
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        // subject 主体
        Subject subject = SecurityUtils.getSubject();

        // 创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken("xiaoming", "123123");
        try {
            // 用户认证
            subject.login(token);
            System.out.println("登录成功!");


        }catch (UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            System.out.println("密码错误");
        }
    }
}
