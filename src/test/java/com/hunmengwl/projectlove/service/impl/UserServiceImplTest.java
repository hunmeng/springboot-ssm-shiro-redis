package com.hunmengwl.projectlove.service.impl;

import com.hunmengwl.projectlove.ProjectloveApplicationTests;
import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest{

    @Resource(name = "userService")
    private IUserService userService;
    private User user;

    @Before
    public void doNew(){
        user = new User();
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
        user.setUserAccount("dawdaw");
        user.setUserPassword("123456");
        int i = userService.insertSelective(user);
        System.out.println("i:"+i);

    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void loadByUser() {
        user.setUserPhone("18569402884");
        User user = userService.loadByUser(this.user);
        System.out.println(user);
    }

    @Test
    public void listUser() {
        List<User> users = userService.listUser(user, null);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}