package com.spring.app.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018-11-06.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "get")
    public String get (String name) {
        return "get: " + name;
    }


}
