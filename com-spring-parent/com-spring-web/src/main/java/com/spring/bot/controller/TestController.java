package com.spring.bot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2018-09-29.
 */
@Controller
@RequestMapping(value = "book")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public String post (String name, String age, HttpServletRequest request){
        String key = request.getHeader("key");
        return name + age + key;
    }

    @ResponseBody
    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get (String name, String age, HttpServletRequest request){
        String key = request.getHeader("key");
        return name + age + key;
    }


}
