package com.chen.cn;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    private final Map<String ,String > userInfoMap = new HashMap<>();
    {
        userInfoMap.put("chen","123");
        userInfoMap.put("root","123");
    }
    private final Map<String ,Set<String> > permissionMap = new HashMap<>();
    {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("video:find");
        set1.add("video:buy");
        set2.add("video:add");
        set2.add("video:delete");
        permissionMap.put("chen",set1);
        permissionMap.put("root",set2);


    }

    private final Map<String ,Set<String> > roleMap = new HashMap<>();
    {

        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();

        set1.add("role1");
        set1.add("role2");
        set2.add("admin");
        roleMap.put("chen",set1);
        roleMap.put("root",set2);
    }



   //权限校验  授权  是否权限判断时调用
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("权限 AuthorizationInfo==================");
        String name = (String )principalCollection.getPrimaryPrincipal();

        Set<String> permissions = getPermissionByNameFromDB(name);
        Set<String> roles = getRolesByNameFromDB(name);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }




    //登录认证   当用户登录的时候调用
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        System.out.println("身份认证AuthenticationInfo =====================");

        //从token 获取身份信息 token代表用户输入的信息
        String name = (String )authenticationToken.getPrincipal();
        //模拟冲数据库取
        String password = getPWdByUsername(name);
        if( password == null || "".equals(password)){
            return null;
        }

        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name,password,this.getName());
        return simpleAuthenticationInfo;
    }

    private String getPWdByUsername(String name) {
        return userInfoMap.get(name);
    }

    private Set<String> getPermissionByNameFromDB(String name) {

        return permissionMap.get(name);
    }

    private Set<String> getRolesByNameFromDB(String name) {
        return roleMap.get(name);
    }
}
