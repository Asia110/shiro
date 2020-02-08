package com.chen.cn.controller;

import com.chen.cn.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class AdminController {

    @RequestMapping("/video/order")
    public JsonData findMyPlayRecrd(){
        Map<String ,Object> map = new HashMap<>();
        map.put("java 从入门到放弃","300元");
        map.put("MySql 从删库到跑路","300大洋");

        return JsonData.buildSuccess(map);
    }
}
