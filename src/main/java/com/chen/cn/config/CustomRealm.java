package com.chen.cn.config;

import com.chen.cn.domain.Permission;
import com.chen.cn.domain.Role;
import com.chen.cn.domain.User;
import com.chen.cn.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义realm
 */
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 进行权限校验的时候调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        User newUser = (User)principalCollection.getPrimaryPrincipal();
        User user = userService.findAllUserInfoByUsername(newUser.getUserName());
        System.out.println("授权---AuthorizationInfo---" + newUser.getUserName());
        List<String> stringRoleList = new ArrayList<>();
        List<String> stringPermissionList = new ArrayList<>();

        List<Role> roleList = user.getRoleList();

        //存放当前用户的角色、权限信息
        for (Role role : roleList){
            stringRoleList.add(role.getName());
            List<Permission> permissionList = role.getPermissionList();
            for (Permission permission : permissionList){
                if(permission != null){
                    stringPermissionList.add(permission.getName());
                }
            }

        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户登陆的时候调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("认证---AuthenticationInfo");
        //获取当前登录用户名
        String username =(String) authenticationToken.getPrincipal();

        //获取数据库用户密码
        User user = userService.findSimpleUserInfoByUsername(username);
        String password = user.getPassWord();
        if(StringUtils.isEmpty(password)){
            return null;
        }
        return new SimpleAuthenticationInfo(user,password,this.getClass().getName());

    }
}
