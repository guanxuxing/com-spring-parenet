package com.spring.app.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018-11-06.
 */
@Controller
public class LoginController {

    @ResponseBody
    @RequestMapping(value = "login_toRealLogin")
    public String login_toRealLogin(){
        return "user is not exit";
    }

    @ResponseBody
    @RequestMapping(value = "login")
    public String login(String userName, String passWord){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return "login.fail";
        }
        return "login.succes " + session.getId();
    }

    @ResponseBody
    @RequestMapping(value = "loginOut")
    public String loginOut (){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login.out.success";
    }


}
