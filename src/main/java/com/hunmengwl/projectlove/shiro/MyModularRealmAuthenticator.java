package com.hunmengwl.projectlove.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    private static final Logger log = LoggerFactory.getLogger(ModularRealmAuthenticator.class);

    /**
     * 重写doMultiRealmAuthentication防止多realm只抛出AuthenticationException异常
     * 其他异常失效，比如密码错误UnknownAccountException
     * @param realms
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doMultiRealmAuthentication(Collection<Realm> realms, AuthenticationToken token) throws AuthenticationException {
        AuthenticationStrategy strategy = getAuthenticationStrategy();

        AuthenticationInfo aggregate = strategy.beforeAllAttempts(realms, token);

        if (log.isTraceEnabled()) {
            log.trace("Iterating through {} realms for PAM authentication", realms.size());
        }
        AuthenticationException authenticationException = null;
        for (Realm realm : realms) {

            aggregate = strategy.beforeAttempt(realm, token, aggregate);

            if (realm.supports(token)) {

                log.trace("Attempting to authenticate token [{}] using realm [{}]", token, realm);

                AuthenticationInfo info = null;
                try {
                    info = realm.getAuthenticationInfo(token);
                } catch (AuthenticationException e) {
                    authenticationException = e;
                    if (log.isDebugEnabled()) {
                        String msg = "Realm [" + realm + "] threw an exception during a multi-realm authentication attempt:";
                        log.debug(msg, e);
                    }
                }

                aggregate = strategy.afterAttempt(realm, token, info, aggregate, authenticationException);

            } else {
                log.debug("Realm [{}] does not support token {}.  Skipping realm.", realm, token);
            }
        }
        if(authenticationException != null){
            throw authenticationException;
        }
        aggregate = strategy.afterAllAttempts(token, aggregate);

        return aggregate;
    }



    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // TODO Auto-generated method stub

        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的CustomizedToken
//        UserNamePasswordTelphoneToken phoneToken = (UserNamePasswordTelphoneToken) authenticationToken;

        // 所有Realm
        Collection<Realm> realms = getRealms();

//        if (authenticationToken instanceof UsernamePasswordToken ) {
//            phoneToken = (UsernamePasswordToken) authenticationToken;
//        }

        // 判断是单Realm还是多Realm
        System.out.println("realms.size():-----" + realms.size());
        if (realms.size() == 1)
            return doSingleRealmAuthentication(realms.iterator().next(), authenticationToken);
        else
            return doMultiRealmAuthentication(realms, authenticationToken);
    }

}



