package com.spring.bot.biz.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.spring.bot.biz.InterfaceBiz;
import com.spring.bot.controller.base.CommonResponse;
import com.spring.bot.dao.InterfaceParamDao;
import com.spring.bot.dto.InterfaceParamDto;
import com.spring.bot.entity.InterfaceParam;
import com.spring.bot.util.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018-08-27.
 */
@Service
public class InterfaceBizImpl implements InterfaceBiz {

    @Resource
    private InterfaceParamDao interfaceParamDao;

    @Override
    public InterfaceParam getOntByCallee(String callee) {
        return interfaceParamDao.getOneByCallee(callee);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void addInterfaceParam(InterfaceParam interfaceParam) throws Exception {
        String str = null;
        Integer key =  interfaceParamDao.addInterfaceParam(interfaceParam);
        System.out.println("-----------" + interfaceParam.toString() + ",key" + (Integer)key);
    }

    @Override
    public void addList(List<InterfaceParam> list) {
        list = new ArrayList<>();
        InterfaceParam param = new InterfaceParam();
        param.setCallee("2324");
        list.add(param);
        InterfaceParam param1 = new InterfaceParam();
        param1.setCallee("2325");
        list.add(param1);
        Integer result = interfaceParamDao.addList(list);
        System.out.println("------------ "  + result);
    }

    /****
     * mybatis 分页插件 PageHelper
     * 代码控制分页
     * @param dto
     * @return
     */
    @Override
    public CommonResponse getList(InterfaceParamDto dto) {
        Page page = PageHelper.startPage(dto.getPage().getPageNum(), dto.getPage().getPageSize(), true);
        List<InterfaceParam> list = interfaceParamDao.getList(null);
        Integer pageNum = page.getPageNum();
        Integer pageSize = page.getPageSize();
        Integer pages = page.getPages();
        Long total = page.getTotal();
        CommonResponse response = new CommonResponse("E0000", list, pageNum, pageSize, pages, total);
        return response;
    }

    @Override
    public CommonResponse listPageInterfaceParam(com.spring.bot.entity.Page page) {
        page.setCurrentPage(1);
        PageData pd = new PageData();
        page.setPd(pd);
        List<PageData> list = interfaceParamDao.listPageInterfaceParam(page);
        return new CommonResponse("E0000", page);
    }
}
