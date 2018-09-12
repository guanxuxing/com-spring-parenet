package com.spring.bot.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * Created by Administrator on 2018-08-30.
 */
@Controller
@RequestMapping(value = "book")
public class BookController {

    @RequestMapping(value = "list")
    public ModelAndView list (){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book_list");
        mv.addObject("name", "zs");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "invoke")
    public String invoke (HttpServletRequest request, HttpServletResponse response) throws Exception{
        InputStream inputStream = request.getInputStream();
        String auth = request.getHeader("auth");
        String requestInfo = IOUtils.toString(inputStream);
        response.getOutputStream().print("678");
        //response.getWriter().write("success");
        return "1234";
    }

}
