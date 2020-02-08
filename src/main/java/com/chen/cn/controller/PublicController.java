package com.chen.cn.controller;


import com.chen.cn.domain.JsonData;
import com.chen.cn.domain.UserQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("pub")
public class PublicController {

    @RequestMapping("need_login")
    public JsonData needLogin(){
        return  JsonData.buildSuccess("温馨提示：请使用对应的账号登录",-2);
    }

    @RequestMapping("not_permit")
    public JsonData notPermit(){
        return JsonData.buildSuccess("温馨提示：拒绝访问，没有权限",-3);
    }

    @RequestMapping("index")
    public JsonData index(){

        List<String> videoList = new ArrayList<>();
        videoList.add("Mysql零基础入门到实战 数据库教程");
        videoList.add("Redis高并发高可用集群百万级秒杀实战");
        videoList.add("Zookeeper+Dubbo视频教程 微服务教程分布式教程");
        videoList.add("2019年新版本RocketMQ4.X教程消息队列教程");
        videoList.add("微服务SpringCloud+Docker入门到高级实战");

        return JsonData.buildSuccess(videoList);

    }

    @PostMapping("login")
    public JsonData login(@RequestBody UserQuery userQuery, HttpServletRequest request
                , HttpServletResponse response){

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject+"------------------====="+subject.getPrincipal());
        Map<String ,Object> map = new HashMap<>();
        try {
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(userQuery.getUsername(),userQuery.getPwd());
            subject.login(usernamePasswordToken);
            map.put("msg","登录成功");
            map.put("sessionId",subject.getSession().getId());
            System.out.println(request.getSession().getId()+"-----------"+subject.getSession().getId());
            return JsonData.buildSuccess(map);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","账号或者密码错误");
            return JsonData.buildSuccess(map);
        }


    }
}
