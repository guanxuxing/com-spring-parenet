package com.spring.bot.rmi;

import java.io.Serializable;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Administrator on 2018-10-25.
 */
public class HelloImpl implements Serializable,IHello {

    @Override
    public void sys() throws Exception {
        System.out.println("java.rmi.success");

    }
}
