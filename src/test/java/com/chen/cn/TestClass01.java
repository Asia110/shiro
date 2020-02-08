package com.chen.cn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


public class TestClass01 {



    private SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();

    @Before
    public void init(){
        simpleAccountRealm.addAccount("chen","123");
        simpleAccountRealm.addAccount("root","root");
        defaultSecurityManager.setRealm(simpleAccountRealm);

    }

    @Test
    public void testAuthentication(){

        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("chen","123");

        subject.login(usernamePasswordToken);

        System.out.println("登录结果--="+subject.isAuthenticated());

    }

}
