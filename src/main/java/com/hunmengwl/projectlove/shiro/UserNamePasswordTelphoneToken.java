package com.hunmengwl.projectlove.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class UserNamePasswordTelphoneToken extends UsernamePasswordToken implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4812793519945855483L;

    // 手机号码
    private String phone;

    /**
     * 重写getPrincipal方法
     */
    public Object getPrincipal() {
        // TODO Auto-generated method stub
        // 如果获取到用户名，则返回用户名，否则返回电话号码
        if (phone == null) {
            return getUsername();
        } else {
            return getTelphoneNum();
        }
    }

    /**
     * 重写getCredentials方法
     */
    public Object getCredentials() {
        // TODO Auto-generated method stub
        // 如果获取到密码，则返回密码，否则返回null
        if (phone == null) {
            return getPassword();
        } else {
            return "ok";
        }
    }

    public UserNamePasswordTelphoneToken() {
        // TODO Auto-generated constructor stub
    }

    public UserNamePasswordTelphoneToken(final String telphoneNum) {
        // TODO Auto-generated constructor stub
        this.phone = telphoneNum;
    }

    public UserNamePasswordTelphoneToken(final String userName, final String password) {
        // TODO Auto-generated constructor stub
        super(userName, password);
    }

    public String getTelphoneNum() {
        return phone;
    }

    public void setTelphoneNum(String telphoneNum) {
        this.phone = telphoneNum;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public String toString() {
        return "TelphoneToken [telphoneNum=" + phone + "]";
    }

}

