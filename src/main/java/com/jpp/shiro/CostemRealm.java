package com.jpp.shiro;

import com.jpp.entity.Pers;
import com.jpp.entity.Role;
import com.jpp.entity.User;
import com.jpp.service.UserService;
import com.jpp.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author lcv8
 * @date 2021.02.19
 * 自定义realm
 */

public class CostemRealm extends AuthorizingRealm {
    /**
     * 授权
     * */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //获取身份信息
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();
        //根据主身份信息获取角色 和 权限
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User users = userService.findRolesByUsername(primaryPrincipal);

        //赋值角色权限
        if(!ObjectUtils.isEmpty(users.getRoles())){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            users.getRoles().forEach(role -> {
                info.addRole(role.getName());
                List<Pers> roleId = userService.findPerByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(roleId)){
                    roleId.forEach(rid -> {
                        info.addStringPermission(rid.getName());
                    });
                }
            });
            return info;
        }
        return null;
    }

    /**
     * 认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String info = (String) token.getPrincipal();
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userServiceImpl");
        User user = userService.findByUsername(info);
        System.out.println(user);
        if(!ObjectUtils.isEmpty(user)){
            return new SimpleAuthenticationInfo(user.getUsername(),
                    user.getPassword(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}
