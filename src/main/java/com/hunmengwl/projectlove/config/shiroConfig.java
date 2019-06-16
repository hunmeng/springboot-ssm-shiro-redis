package com.hunmengwl.projectlove.config;

import cn.hutool.setting.dialect.Props;
import com.hunmengwl.projectlove.redis.KickoutSessionControlFilter;
import com.hunmengwl.projectlove.redis.MySessionManager;
import com.hunmengwl.projectlove.redis.RedisCacheManager;
import com.hunmengwl.projectlove.redis.RedisSessionDAO;
import com.hunmengwl.projectlove.service.IUserService;
import com.hunmengwl.projectlove.service.impl.UserServiceImpl;
import com.hunmengwl.projectlove.shiro.MyModularRealmAuthenticator;
import com.hunmengwl.projectlove.shiro.PhoneRealm;
import com.hunmengwl.projectlove.shiro.UserRealm;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class shiroConfig {

    @Value("${shiro.redis.sessionLive}")
    private long sessionLive;
    @Value("${shiro.redis.sessionPrefix}")
    private String sessionPrefix;
    @Value("${shiro.redis.cacheLive}")
    private long cacheLive;
    @Value("${shiro.redis.cachePrefix}")
    private String cachePrefix;
    @Value("${shiro.redis.kickoutPrefix}")
    private String kickoutPrefix;


    /**
     * 自定义shiro cache管理
     *
     * @return
     */
    @Bean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        //cache过期时间及前缀
        redisCacheManager.setCacheLive(cacheLive);
        redisCacheManager.setCacheKeyPrefix(cachePrefix);
        redisCacheManager.setRedisTemplate(redisTemplate);
        return redisCacheManager;
    }

    /**
     * 凭证匹配器
     *
     * @param cacheManager
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher credentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher ();
//        credentialsMatcher.setCacheName("passwordRetryCache");  //指定缓存对象的名字
        //~此处加密方式要与用户注册时的算法一致
        credentialsMatcher.setHashAlgorithmName("md5");     //指定hash算法为MD5
        credentialsMatcher.setHashIterations(1024);     //指定散列次数为1024次
        credentialsMatcher.setStoredCredentialsHexEncoded(true);    //true指定Hash散列值使用Hex加密存. false表明hash散列值用用Base64-encoded存储
        return credentialsMatcher;
    }

    /**
     * 自定义shiro session
     *
     * @return
     */
    @Bean(name = "redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(JavaUuidSessionIdGenerator sessionIdGenerator, RedisTemplate redisTemplate) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setSessionIdGenerator(sessionIdGenerator);
        //session过期时间及前缀
        redisSessionDAO.setSessionLive(sessionLive);
        redisSessionDAO.setSessionKeyPrefix(sessionPrefix);
        redisSessionDAO.setRedisTemplate(redisTemplate);
        return redisSessionDAO;
    }
    /**
     * 自定义sessionManager
     *
     * @return
     */
    @Bean(name = "sessionManager")
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO) {
        MySessionManager mySessionManager = new MySessionManager();
        mySessionManager.setSessionDAO(redisSessionDAO);
        return mySessionManager;
    }

    /**
     * Session ID生成管理器
     *
     * @return
     */
    @Bean(name = "sessionIdGenerator")
    public JavaUuidSessionIdGenerator sessionIdGenerator() {
        JavaUuidSessionIdGenerator sessionIdGenerator = new JavaUuidSessionIdGenerator();
        return sessionIdGenerator;
    }

    /**
     * 安全管理器
     *
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager defaultWebSecurityManager(@Qualifier("phoneRealm") PhoneRealm phoneRealm,
                                                     @Qualifier("userRealm") UserRealm userRealm,
                                                     @Qualifier("myModularRealmAuthenticator")MyModularRealmAuthenticator myModularRealmAuthenticator,
                                                     SessionManager sessionManager,
                                                     RedisCacheManager redisCacheManager ) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
//        <!-- 单realm的配置 -->
//        defaultWebSecurityManager.setRealm(userRealm);
//        <!-- 多realm的配置 -->
        defaultWebSecurityManager.setAuthenticator(myModularRealmAuthenticator);
        List<Realm> realms = new ArrayList<Realm>();
        realms.add(userRealm);
        realms.add(phoneRealm);
        defaultWebSecurityManager.setRealms(realms);
        defaultWebSecurityManager.setSessionManager(sessionManager);
        defaultWebSecurityManager.setCacheManager(redisCacheManager);
        return defaultWebSecurityManager;
    }

    @Bean(name = "kickoutSessionControlFilter")
    public KickoutSessionControlFilter jwtFilter(SessionManager sessionManager, RedisTemplate redisTemplate) {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        kickoutSessionControlFilter.setSessionManager(sessionManager);
        kickoutSessionControlFilter.setRedisTemplate(redisTemplate);
        kickoutSessionControlFilter.setKickoutPrefix(kickoutPrefix);
        return kickoutSessionControlFilter;
    }

    /**
     * Shiro的Web过滤器
     *
     * @param manager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager,KickoutSessionControlFilter kickoutSessionControlFilter) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new HashedMap(2);
        filters.put("kickout", kickoutSessionControlFilter);
        bean.setFilters(filters);
        //访问需要认证的地址时，没有认证跳转的地址，默认跳网站首页，然后经springmvc转到/WEB-INF/jsp/login.jsp
        bean.setLoginUrl("/");
//        bean.setSuccessUrl("/index");
        bean.setUnauthorizedUrl("/404");  //登录后，没有访问权限将跳转的地址
        Map<String, String> map = new LinkedHashMap();
        map.put("/css/**", "anon");
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/fonts/**", "anon");
        map.put("/", "anon");
        map.put("/user/logout","logout");
        map.put("/userInfo/**","authc");
        map.put("/dict/**","authc");
//        map.put("/console/**","roles[admin]");
        map.put("/**", "kickout,anon");
        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    /**
     * Realm实现
     *
     * @param credentialsMatcher
     * @param iUserService
     * @return
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(@Qualifier("userService")IUserService userService,@Qualifier("credentialsMatcher") HashedCredentialsMatcher credentialsMatcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher);
        userRealm.setUserService(userService);
        userRealm.setCachingEnabled(true);
        return userRealm;
    }
    @Bean(name = "phoneRealm")
    public PhoneRealm phoneRealm(@Qualifier("userService")IUserService userService) {
        PhoneRealm phoneRealm = new PhoneRealm();
        phoneRealm.setUserService(userService);
        phoneRealm.setCachingEnabled(true);
        return phoneRealm;
    }

    /**
     *  配置多个realm的时候如何认证
     * @param atLeastOneSuccessfulStrategy
     * @return
     */
    @Bean(name = "myModularRealmAuthenticator")
    public MyModularRealmAuthenticator myModularRealmAuthenticator(){
        MyModularRealmAuthenticator myModularRealmAuthenticator = new MyModularRealmAuthenticator();
        myModularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return myModularRealmAuthenticator;
    }

    /**
     * 生命周期
     *
     * @return
     */
//    @Bean(name = "lifecycleBeanPostProcessor")
//    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
//        return new LifecycleBeanPostProcessor();
//    }

/**
 * 不加这个报错
 * @return
 */
    @Bean
    public FilterRegistrationBean delegatingFilterProxy() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     * @return
     */
//    @Bean
//    public ShiroDialect shiroDialect() {
//        return new ShiroDialect();
//    }

/**
 * 下面2个支持controller层注解实现权限控制
 *
 * @return
 */
    @Bean(name = "advisorAutoProxyCreator")
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean(name = "authorizationAttributeSourceAdvisor")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     *   shiro 在spring的unauthorizedUrl配置后不起作用
         原因：这是因为shiro源代码中判断了filter是否为AuthorizationFilter，只有perms，roles，ssl，rest
         port才是属于AuthorizationFilter，而anon，authcBasic，auchc，user是AuthenticationFilter
         所以unauthorizedUrl设置后不起作用。
         解决方案：注入对应异常处理
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.put("org.apache.shiro.authz.UnauthorizedException","404");
//        properties.put("org.apache.shiro.authz.AuthorizationException","index");
        resolver.setExceptionMappings(properties);
        return resolver;
    }

}


