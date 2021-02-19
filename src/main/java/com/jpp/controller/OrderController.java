package com.jpp.controller;

import com.jpp.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lcv8
 */
@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("admin")
    //@RequiresRoles("admin")  判断角色 数组参数标识两者同时具有
//    @RequiresRoles({"user"})
    @RequiresPermissions("user:delete:01") //判断权限字符串
    public String save(){
        System.out.println("进入方法");
        //获取用户主体
        Subject subject = SecurityUtils.getSubject();
//        if(subject.hasRole("user")){
//            System.out.println("获取成功");
//        } else {
//            System.out.println("获取失败");
//        }
        return "redirect:/index.jsp";
    }
}
