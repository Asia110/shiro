package com.chen.cn;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class CustomRealmTest {

    //构建环境
    private CustomRealm customRealm = new CustomRealm();
    private DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();


    @Before
    public  void  init(){
        defaultSecurityManager.setRealm(customRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);
    }

    @Test
    public void testAuthentication(){

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken("chen","123");

        //登录认证
        subject.login(usernamePasswordToken);

        //权限
        System.out.println("hasRole =="+subject.hasRole("commonUser"));
        //主体
        System.out.println("Principal =="+ subject.getPrincipal());
        //是否已经认证
        System.out.println(" 登录结果--="+subject.isAuthenticated());

        System.out.println("角色判断"+subject.hasRole("role1"));
        System.out.println("权限判断 "+subject.isPermitted("video:buy"));

        subject.logout();
        System.out.println(" 登录结果--="+subject.isAuthenticated());

    }

}
