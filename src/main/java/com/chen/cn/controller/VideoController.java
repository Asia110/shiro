package com.chen.cn.controller;

import com.chen.cn.domain.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("video")
public class VideoController {


    @RequestMapping("/video_update")
    public JsonData orderUpdate(){
        return JsonData.buildSuccess("更新成功！！！");
    }
}
