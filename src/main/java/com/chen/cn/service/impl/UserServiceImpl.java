package com.chen.cn.service.impl;

import com.chen.cn.dao.RoleMapper;
import com.chen.cn.dao.UserMapper;
import com.chen.cn.domain.Role;
import com.chen.cn.domain.User;
import com.chen.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User findAllUserInfoByUsername(String username) {

        User user = userMapper.findUserByUserName(username);

        List<Role> roles =roleMapper.findRoleListByUserId(user.getId());
        user.setRoleList(roles);
        return user;
    }

    @Override
    public User findSimpleUserInfoById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public User findSimpleUserInfoByUsername(String username) {
        return userMapper.findUserByUserName(username);
    }
}
