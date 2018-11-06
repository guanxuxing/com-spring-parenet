package com.spring.bot.rmi;

import java.rmi.Remote;

/**
 * Created by Administrator on 2018-10-25.
 */
public interface IHello extends Remote {

    public void sys() throws Exception;
}
