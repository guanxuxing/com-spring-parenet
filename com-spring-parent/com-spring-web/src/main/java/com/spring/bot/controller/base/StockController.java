package com.spring.bot.controller.base;

import com.spring.bot.biz.StockBiz;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2018-09-20.
 */
@Controller
@RequestMapping(value = "book")
public class StockController {

    @Resource
    private StockBiz stockBiz;

    @RequestMapping(value = "sale")
    public void sale (){
        stockBiz.service();
    }
}
