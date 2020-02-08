package com.chen.cn.config;

import com.chen.cn.domain.Role;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomRolesOrAuthorizationFilter extends AuthorizationFilter {


    @SuppressWarnings("unchecked")
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
        Subject subject = this.getSubject(request, response);

        //获取当前用户角色列表
        String[] rolesArray = (String[])(mappedValue);

        //没有角色限制 可以直接访问
        if(rolesArray == null || rolesArray.length == 0){
            return true;
        }

        //只要用户拥有当前任意角色 通过访问
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for(String role :roles){
            if(subject.hasRole(role)){
                return true;
            }
        }
        return false;

    }
}
