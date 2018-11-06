package com.spring.bot.rmi;

import com.spring.bot.rmi.method.RmiMethod;

import java.rmi.Naming;

/**
 * Created by Administrator on 2018-10-25.
 */
public class RmiClient {
    public static void main(String[] args) throws Exception{
        IHello rhello =  (IHello) Naming.lookup("rmi://localhost:7744/RHello");
        rhello.sys();

    }
}
