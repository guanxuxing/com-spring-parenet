package com.spring.app.web.controller;

import com.spring.app.kfk.KfkProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018-11-09.
 */
@Controller
public class UserController {

    @Resource
    private KfkProducer kfkProducer;

    @ResponseBody
    @RequestMapping(value = "server")
    public void server () throws Exception{
        kfkProducer.sendMsg();
    }

}
