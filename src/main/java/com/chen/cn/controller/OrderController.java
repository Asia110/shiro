package com.chen.cn.controller;

import com.chen.cn.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("authc")
public class OrderController {

    @RequestMapping("/video/play_record")
    public JsonData findMyPlayRecrd(){
        Map<String ,Object> map = new HashMap<>();
        map.put("java 从入门到放弃","第八集");
        map.put("MySql 从删库到跑路","第一季第一章");

        return JsonData.buildSuccess(map);
    }

    @RequestMapping("/video/video_update")
    public JsonData orderUpdate(){

        return JsonData.buildSuccess("更新成功！！！");
    }
}
