package com.hunmengwl.projectlove.model;

import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 6015002150131040308L;
    private Integer userId;

    private String userAccount;

    private String userPassword;

    private String userSalt;

    private String userImage;

    private String userSex;

    private String userPhone;

    private Date userCreatetime;

    private Integer userStatus;

    public User(Integer userId, String userAccount, String userPassword, String userSalt, String userImage, String userSex, String userBirthday, Date userCreatetime, Integer userStatus) {
        this.userId = userId;
        this.userAccount = userAccount;
        this.userPassword = userPassword;
        this.userSalt = userSalt;
        this.userImage = userImage;
        this.userSex = userSex;
        this.userPhone = userBirthday;
        this.userCreatetime = userCreatetime;
        this.userStatus = userStatus;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserCreatetime() {
        return userCreatetime;
    }

    public void setUserCreatetime(Date userCreatetime) {
        this.userCreatetime = userCreatetime;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
}