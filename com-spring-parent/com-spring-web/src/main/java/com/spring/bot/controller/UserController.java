package com.spring.bot.controller;

import com.alibaba.fastjson.JSONObject;
/*import com.spring.bot.activeMQ.CreateDocProducer;*/
import com.spring.bot.biz.InterfaceBiz;
import com.spring.bot.controller.base.CommonResponse;
import com.spring.bot.dto.BaseDto;
import com.spring.bot.dto.InterfaceParamDto;
import com.spring.bot.entity.InterfaceParam;
import com.spring.bot.entity.Page;
import com.spring.bot.entity.UserInfo;
import com.spring.bot.service.UserService;
import com.spring.bot.util.ExcelUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018-08-24.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private InterfaceBiz interfaceBiz;

  /*  @Resource
    private CreateDocProducer createDocProducer;*/


    @ResponseBody
    @RequestMapping(value = "showInfo")
    public String showInfo (BaseDto dto) {
        return userService.getInfo();
    }

    @ResponseBody
    @RequestMapping(value = "list1")
    public UserInfo list () {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("gxx");
        userInfo.setMobile("131");
        return userInfo;
    }

    @ResponseBody
    @RequestMapping(value = "list2")
    public InterfaceParam getInfo () {
        return interfaceBiz.getOntByCallee("666666");
    }

    @ResponseBody
    @RequestMapping(value = "list3")
    public String addInterfaceParam1() throws Exception{
        InterfaceParam param = new InterfaceParam();
        param.setCallee("123456");
        interfaceBiz.addInterfaceParam(param);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "list4")
    public String addList() throws Exception{
        interfaceBiz.addList(null);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "list5")
    public String sendMsg() throws Exception{
        BaseDto dto = new BaseDto();
        dto.setName("gxx");
        dto.setMobile("186");
        dto.setCardNo("621226");
       // createDocProducer.sendMessage(dto);
        return "success";
    }

    @ResponseBody
    @RequestMapping(value = "list6")
    public CommonResponse showInfo() throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile("zs");
        CommonResponse response = new CommonResponse("E0000", userInfo);
        System.out.println("----------------- " + JSONObject.toJSONString(response));
        return new CommonResponse("E0001", userInfo);
    }

    @RequestMapping(value = "list7")
    public void excel(HttpServletResponse response) throws Exception{
        UserInfo userInfo = new UserInfo();
        userInfo.setName("zs");
        userInfo.setMobile("186");
        List<UserInfo> list=new ArrayList<>();
        list.add(userInfo);
        String labels[]={"姓名","手机号码"};
        String cell[]={"name","mobile"};

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + new Date().getTime() +"_contract"+".xlsx");
        Workbook workbook1= ExcelUtils.generateWorkbook(labels,cell,list);
        OutputStream outputStream=response.getOutputStream();
        workbook1.write(outputStream);
        if(outputStream!=null){
            outputStream.close();
        }
    }

    @ResponseBody
    @RequestMapping(value = "list8")
    public CommonResponse page(InterfaceParamDto dto){
       return interfaceBiz.getList(dto);
    }

    @RequestMapping(value = "list")
    public ModelAndView listPageInterfaceParam(Page page){
        interfaceBiz.listPageInterfaceParam(page);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/list");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "list9")
    public CommonResponse listPageInterfaceParam1(Page page){
        CommonResponse response = interfaceBiz.listPageInterfaceParam(page);
        return response;
    }

}
