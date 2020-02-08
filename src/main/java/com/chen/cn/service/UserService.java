package com.chen.cn.service;

import com.chen.cn.domain.User;

public interface UserService {

    /**
     * 获取全部用户信息、包括角色、权限
     * @param username
     * @return
     */
    User findAllUserInfoByUsername(String username);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User findSimpleUserInfoById(Integer id);

    /**
     * 根据用户姓名获取用户信息
     * @param username
     * @return
     */
    User findSimpleUserInfoByUsername(String username);

}
