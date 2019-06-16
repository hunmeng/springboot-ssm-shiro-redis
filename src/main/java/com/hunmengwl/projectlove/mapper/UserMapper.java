package com.hunmengwl.projectlove.mapper;

import com.hunmengwl.projectlove.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);//根据主键查询

    User loadByUser(User user);//输入什么查询什么

    List<User> listUser(User user);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsername(String username);

    List<String> listPermissionsByUserName(User user);//查询指定用户拥有的权限

    List<String> listRolesByUserName(User user);//查询指定用户拥有的角色




}