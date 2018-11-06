package com.spring.bot.rmi.method;

import java.io.Serializable;
import java.rmi.Remote;

/**
 * Created by Administrator on 2018-10-25.
 */
public class RmiMethod implements Serializable,Remote {

    public void sys (){
        System.out.println("java.rmi.method.sys...");
    }
}
