package com.hunmengwl.projectlove.shiro;

import com.hunmengwl.projectlove.model.User;
import com.hunmengwl.projectlove.service.IUserService;
import com.hunmengwl.projectlove.service.impl.UserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

/**
 * 自定义Realm
 */
public class UserRealm extends AuthorizingRealm {

    private static final Integer LOCKED = new Integer(0);

    //用户对应的角色信息与权限信息都保存在数据库中，通过IUserBiz获取数据
    private IUserService userService;

    public UserRealm() {
    }

    public UserRealm(CacheManager cacheManager) {
        super(cacheManager);
    }

    public UserRealm(CredentialsMatcher matcher) {
        super(matcher);
    }

    public UserRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 返回一个唯一的Realm名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "UserRealm";
    }

    /**
     * 判断此Realm是否支持此Token
     *
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
//        return token!=null&&getAuthenticationTokenClass().isAssignableFrom(token.getClass());
        return token instanceof UsernamePasswordToken;//仅支持UsernamePasswordToken类型的Token
    }

    /**
     * 提供用户信息返回授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //查询用户已授予的角色及权限
        String username = (String) principalCollection.getPrimaryPrincipal();//拿到保存在subject.principalCollection里的第一个token
        System.out.println("username:"+username);
        User user = new User();
        user.setUserAccount(username);
        Set<String> permissions = userService.listPermissionsByUserName(user);
        Set<String> roles = userService.listRolesByUserName(user);

        //返回授权信息
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);//
        authorizationInfo.setStringPermissions(permissions);
        for (String s : authorizationInfo.getRoles()) {
            System.out.println("getRoles:"+s);
        }
        for (String s : authorizationInfo.getStringPermissions()) {
            System.out.println("getStringPermissions:"+s);
        }
        return authorizationInfo;
    }

    /**
     * 提供用户信息返回认证信息
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //查询用户已授予的角色及权限
        System.out.println("----------------进来了这里--------------------------------");
//        setCredentialsMatcher(credentialsMatcher);
        String username = (String) authenticationToken.getPrincipal();
        User u = userService.selectByUsername(username);
        if (null == u) {
            throw new UnknownAccountException();//帐号不存在
        }
        if (LOCKED.equals(u.getUserStatus())) {
            throw new LockedAccountException(); //帐号已锁定
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                u.getUserAccount(),
                u.getUserPassword(),
                ByteSource.Util.bytes(u.getUserSalt()),
                getName()
        );
        return authenticationInfo;
    }

}
