package com.hunmengwl.projectlove.controller;

import com.hunmengwl.projectlove.commons.CodeAndMsgEnum;
import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import com.hunmengwl.projectlove.shiro.UserNamePasswordTelphoneToken;
import com.hunmengwl.projectlove.utils.ResponseEntity;
import com.hunmengwl.projectlove.utils.ResponseResult;
import com.hunmengwl.projectlove.utils.yzm.controller.ValidateCodeController;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {

    @Resource(name = "userService")
    private IUserService userService;

    @RequestMapping(value = "/login")
    public String login(User user,String yzm,HttpSession session) {
        System.out.println(user);
        String sessionKey = (String)session.getAttribute(ValidateCodeController.SESSION_KEY);//key
        if (sessionKey!=null && !sessionKey.equals("") && sessionKey.equals(yzm.toUpperCase())) {
            return userService.loginUser(user);
        }else {
            return "0";
        }
    }

    @PostMapping("/doPhoneYzm")
    public  Map getPhoneYzm(String phone,HttpSession session) throws InterruptedException {
        String yzm = "666666";
//        responseResult.setStatus();
        Thread.sleep(5000);
        session.setAttribute(phone,yzm);
        return ResponseEntity.responseSuccess("发送验证码成功");
    }



    // 使用手机号和短信验证码登录
    @RequestMapping("/loginPhone")
    public Map pLogin( String phone,String yzm, HttpSession session){
        System.out.println("phone"+phone+"---------"+"yzm:"+yzm);
        // 根据phone从session中取出发送的短信验证码，并与用户输入的验证码比较
        // session.setAttribute("phone","123456");
        String messageCode = (String) session.getAttribute(phone);

        if( messageCode!=null && !messageCode.equals("") && messageCode.equals(yzm)){
             return  userService.regUser(phone);
        }else{
            Map map = new HashMap();
            map.put("code", "no");
            return map;
        }
    }

    @PostMapping("/regist")
    public String userReg(User user,String yzm,HttpSession session){
        if (yzm.toUpperCase().equals(session.getAttribute(ValidateCodeController.SESSION_REG_KEY))) {
            int i = userService.insertSelective(user);
            return String.valueOf(i);
        }else{
            return "yzmNo";
        }
    }


    @RequestMapping("/unauthorized")
    public String unauthorized() {
        return "404";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }

//    @PostMapping("/login")
//    public void doLogin(User user){
//        System.out.println("user----:"+user.toString());
//
//    }

}
