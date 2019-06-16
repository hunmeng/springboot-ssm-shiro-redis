package com.hunmengwl.projectlove.controller;

import com.hunmengwl.projectlove.utils.ResponseEntity;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @RequestMapping("/index")
    @RequiresPermissions("system:dict:add")
    public Map doUser(){
        System.out.println("进来了 。。。doUser");
        return ResponseEntity.responseSuccess("");
    }

    @RequestMapping("/test")
    @RequiresRoles("admin")
    @RequiresPermissions("system:user:updatePassword")
    public Map dTest(){
        System.out.println("进来了 。。。doUser");
        return ResponseEntity.responseSuccess("");
    }


}
