package com.tl.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @GetMapping(value = "test1")
    public String testThymeleaf(Model model) {
        model.addAttribute("name", "张三");
        return "test";
    }

    @GetMapping(value = "add")
    public String add() {
        return "/user/add";
    }

    @GetMapping(value = "update")
    public String update() {
        return "/user/update";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "login")
    public String login(Model model, String name, String password) {
        // 1.获取Shiro编写认证操作
        Subject subject = SecurityUtils.getSubject();

        // 2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        // 3.执行登录方法
        try {
            subject.login(token);
            return "redirect:test1";
        } catch (UnknownAccountException e) {
            // 登录失败
            model.addAttribute("message", "用户不存在");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("message", "密码错误");
            return "login";
        }

    }
}
