package com.chen.cn.dao;

import com.chen.cn.domain.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PermissionMapper {

    @Select("select p.id as id ,p.name as name, p.url as url from  role_permission rp " +
            " LEFT JOIN permission p on rp.permission_id = p.id" +
            " where rp.role_id=#{roleId}")
    List<Permission> findPermissionListByRoleId(@Param("roleId") Integer roleId);
}
