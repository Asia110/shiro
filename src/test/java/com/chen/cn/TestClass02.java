package com.chen.cn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class TestClass02 {

    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        simpleAccountRealm.addAccount("chen","123","commonUser");
        simpleAccountRealm.addAccount("root","root","adminUser");
        defaultSecurityManager.setRealm(simpleAccountRealm);

    }

    @Test
    public void testAuthentication(){

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("chen","123");

        //登录认证
        subject.login(usernamePasswordToken);


        //权限

        System.out.println("hasRole =="+subject.hasRole("commonUser"));
        System.out.println("Principal =="+ subject.getPrincipal());

        System.out.println(" 登录结果--="+subject.isAuthenticated());
        subject.logout();

        System.out.println(" 登录结果123123--="+subject.isAuthenticated());
    }

}
