package com.chen.cn.controller;

import com.chen.cn.domain.User;
import com.chen.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserAllInfo")
    public User getUserInfo(String username){

        return userService.findAllUserInfoByUsername(username);
    }


}
