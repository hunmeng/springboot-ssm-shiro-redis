package com.hunmengwl.projectlove.service.impl;

import com.hunmengwl.projectlove.ProjectloveApplicationTests;
import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.Assert.*;

public class UserImplTest extends ProjectloveApplicationTests {

    @Resource(name = "userService")
    private IUserService userService;
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void selectByUsername() {
        user.setUserPhone("");
        User ls = userService.selectByUsername("ls");
        System.out.println(ls);
    }

    @Test
    public void listPermissionsByUserName() {
    }

    @Test
    public void listRolesByUserName() {
    }
}