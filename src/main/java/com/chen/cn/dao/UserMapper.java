package com.chen.cn.dao;

import com.chen.cn.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Select("select * from user where username=#{userName}")
    User findUserByUserName(@Param("userName")String username);

    @Select("select * from user where id =#{id}")
    User findUserById(@Param("id") Integer id);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findUserByUserNameAndPassword(@Param("username") String username,@Param("password")String password);
}
