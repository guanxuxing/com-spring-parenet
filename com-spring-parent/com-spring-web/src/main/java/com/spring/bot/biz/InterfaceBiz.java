package com.spring.bot.biz;

import com.spring.bot.controller.base.CommonResponse;
import com.spring.bot.dto.InterfaceParamDto;
import com.spring.bot.entity.InterfaceParam;

import java.util.List;

/**
 * Created by Administrator on 2018-08-27.
 */
public interface InterfaceBiz {

    public InterfaceParam getOntByCallee(String callee);

    public void addInterfaceParam(InterfaceParam interfaceParam) throws Exception;

    public void addList(List<InterfaceParam> list);

    public CommonResponse getList(InterfaceParamDto dto);

    public CommonResponse listPageInterfaceParam (com.spring.bot.entity.Page page);
}
