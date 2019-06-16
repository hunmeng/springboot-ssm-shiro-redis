package com.hunmengwl.projectlove.service;

import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.utils.PageBean;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserService {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    String loginUser(User user);

    Map regUser(String phone);

    User loadByUser(User user);//输入什么查询什么

    List<User> listUser(User user, PageBean pageBean);
    //权限关键接口
    User selectByUsername(String acouut);//根据用户名查询用户
    Set<String> listPermissionsByUserName(User user);//查询指定用户拥有的权限
    Set<String> listRolesByUserName(User user);//查询指定用户拥有的角色
}