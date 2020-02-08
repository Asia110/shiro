package com.chen.cn.dao;

import com.chen.cn.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface RoleMapper {

    @Select("select r.id as id,r.name as name ,r.description as description from  user_role ur" +
            " LEFT JOIN role r on ur.role_id=r.id" +
            " where ur.user_id=#{userId}")
    @Results(
            value = {
                    @Result(id = true ,property = "id" ,column = "id"),
                    @Result(property = "name" ,column = "name"),
                    @Result(property = "description" ,column = "description"),
                    @Result(property = "permissionList" ,column = "id",
                            many =@Many(select = "com.chen.cn.dao.PermissionMapper.findPermissionListByRoleId",fetchType = FetchType.DEFAULT) )
            }
    )
    List<Role> findRoleListByUserId(@Param("userId")Integer userId);
}
