package com.hunmengwl.projectlove.commons;

import org.apache.shiro.authc.AuthenticationException;

public class LoginPhoneException extends AuthenticationException {

    public LoginPhoneException(){
//        super();
        System.out.println("短信登录查询不到");
    }

    @Override
    public void printStackTrace() {
        System.out.println("短信登录查询不到。。。");
    }
}
