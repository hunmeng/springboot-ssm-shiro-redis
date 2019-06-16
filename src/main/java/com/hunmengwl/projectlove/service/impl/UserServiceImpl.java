package com.hunmengwl.projectlove.service.impl;

import com.hunmengwl.projectlove.commons.CodeAndMsgEnum;
import com.hunmengwl.projectlove.commons.LoginPhoneException;
import com.hunmengwl.projectlove.mapper.UserMapper;
import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import com.hunmengwl.projectlove.shiro.PasswordHelper;
import com.hunmengwl.projectlove.shiro.UserNamePasswordTelphoneToken;
import com.hunmengwl.projectlove.utils.PageBean;
import com.hunmengwl.projectlove.utils.ResponseEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        String salt = PasswordHelper.createSalt();//获取salt——盐
        String credentials = PasswordHelper.createCredentials(record.getUserPassword(), salt);//根据salt进行加密
        record.setUserSalt(salt);
        record.setUserPassword(credentials);
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public String loginUser(User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserAccount(), user.getUserPassword());
        Subject subject = SecurityUtils.getSubject();
        String message = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {// 捕获未知用户名异常
            message = "帐号错误";
        } catch (LockedAccountException e) {// 捕获错误登录过多的异常
            message = "帐号已锁定，请与管理员联系";
        } catch (IncorrectCredentialsException e) {// 捕获密码错误异常
            message = "密码错误";
        } catch (ExcessiveAttemptsException e) {// 捕获错误登录过多的异常
            message = "多次登录尝试失败，请60秒后再试";
        } catch (AuthenticationException e){
            message = "其他异常错误";
        }
        //判断是否出现错误。。null则没有=登录成功
        if (null == message) {
            Session s = subject.getSession();//此session为org.apache.shiro.session.Session
            s.setAttribute("user_key", userMapper.selectByUsername(user.getUserAccount()));//登陆成功后要保存shiro的会话中，已备之后使用
            return "1";
        } else {
            //model.addAttribute("message", message);
            return message;
        }

    }

    /**
     * 注册
     * @param phone
     * @return
     */
    @Override
    public Map regUser(String  phone) {
        UserNamePasswordTelphoneToken token = new UserNamePasswordTelphoneToken(phone);

        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (LoginPhoneException e){
            Map map = new HashMap();
            map.put("code", CodeAndMsgEnum.INFO.getCode());
            return map;
        }catch (RuntimeException e){
            System.out.println("出现了这个异常。。。但是不管他，因为我也不知道怎么处理");
        }
        User user = new User();
        user.setUserPhone(phone);
        user = userMapper.loadByUser(user);
        Session session1 = subject.getSession();//此session为org.apache.shiro.session.Session
        session1.setAttribute("user_key", user);//登陆成功后要保存shiro的会话中，已备之后使用
        return ResponseEntity.responseSuccess("登录成功...");

    }

    @Override
    public User loadByUser(User user) {
        return userMapper.loadByUser(user);
    }

    @Override
    public List<User> listUser(User user, PageBean pageBean) {
        return userMapper.listUser(user);
    }

    @Override
    public User selectByUsername(String acouut) {
        return userMapper.selectByUsername(acouut);
    }

    @Override
    public Set<String> listPermissionsByUserName(User user) {
        return new HashSet<String>(userMapper.listPermissionsByUserName(user));
    }

    @Override
    public Set<String> listRolesByUserName(User user) {
        return new HashSet<String>(userMapper.listRolesByUserName(user));
    }
}
