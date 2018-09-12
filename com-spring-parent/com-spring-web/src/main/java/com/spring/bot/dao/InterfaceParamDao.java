package com.spring.bot.dao;

import com.spring.bot.entity.InterfaceParam;
import com.spring.bot.entity.Page;
import com.spring.bot.util.PageData;

import java.util.List;

/**
 * Created by Administrator on 2018-08-27.
 */
public interface InterfaceParamDao {

    public InterfaceParam getOneByCallee(String callee);

    public Integer addInterfaceParam(InterfaceParam interfaceParam);

    public Integer addList (List<InterfaceParam> list);

    public List<InterfaceParam> getList(InterfaceParam param);

    public List<PageData> listPageInterfaceParam(Page page);
}
