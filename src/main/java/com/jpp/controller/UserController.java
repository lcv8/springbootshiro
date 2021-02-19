package com.jpp.controller;

import com.jpp.entity.User;
import com.jpp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lcv8
 * @date 2021.02.19
 */

@Controller
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 处理身份认证
     * */
    @PostMapping("login")
    public String login(String username , String password){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username);
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            log.info("用户名错误");
        } catch (IncorrectCredentialsException e){
            e.printStackTrace();
            log.info("密码错误");
        }
        return "redirect:/login.jsp";
    }

    /**
     * 推出登录
     * */
    @RequestMapping("logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }

    /**
     * 用户注册
     * */
    @RequestMapping("register")
    public String register(User user){
        try {
            userService.save(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }
    }
}
