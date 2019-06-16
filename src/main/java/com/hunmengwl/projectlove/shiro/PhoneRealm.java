package com.hunmengwl.projectlove.shiro;

import com.hunmengwl.projectlove.commons.LoginPhoneException;
import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class PhoneRealm extends AuthorizingRealm {


    private IUserService userService;

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("我有进来这里------------");

        UserNamePasswordTelphoneToken token = null;

        // 如果是PhoneToken，则强转，获取phone；否则不处理。
        if(authenticationToken instanceof UserNamePasswordTelphoneToken){
            token = (UserNamePasswordTelphoneToken) authenticationToken;
        }else{
            return null;
        }

        String phone = (String) token.getPrincipal();

        User u = new User();
        u.setUserPhone(phone);
        User user = userService.loadByUser(u);

        if (user == null) {
            throw new LoginPhoneException();
        }
        return new SimpleAuthenticationInfo(user.getUserPhone(), "ok", getName());
    }




    @Override
    public boolean supports(AuthenticationToken var1){
        return var1 instanceof UserNamePasswordTelphoneToken;
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //查询用户已授予的角色及权限
        String username = (String) principals.getPrimaryPrincipal();
        User user = new User();
        user.setUserPhone(username);
        //通过指定用户名查询权限
        Set<String> permissions = userService.listPermissionsByUserName(user);
        //通过指定用户名查询角色
        Set<String> roles = userService.listRolesByUserName(user);

        //返回授权信息

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);//
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }
}

