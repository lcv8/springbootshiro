package com.jpp.config;

import com.jpp.shiro.CostemRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lcv8
 * @date 2021.02.19
 * 整合shiro框架的配置类
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建 shiroFilter
     * 拦截所有请求
     * */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterBean(DefaultSecurityManager defaultSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultSecurityManager);
        //默认认证界面
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        //配置访问受限资源以及公共资源
        Map<String, String> map = new HashMap<>();
        // "anon" 表示这个时公共资源
        map.put("/user/**","anon");
        map.put("/login.jsp","anon");
        map.put("/register.jsp","anon");
        // "authc" 表示请求这个资源需要认证授权
        map.put("/**","authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    /**
     * 创建安全管理器
     * */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("realm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    /**
     * 创建自定义realm
     * */
    @Bean(value = "realm")
    public Realm getRealm(){
        CostemRealm realm = new CostemRealm();
        //修改为md5 + 散列
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        return realm;
    }

}
