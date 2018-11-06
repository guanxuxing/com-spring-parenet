package com.spring.bot.rmi;

import com.spring.bot.rmi.method.RmiMethod;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Created by Administrator on 2018-10-25.
 */
public class RmiServer {

    public static void main(String[] args) throws Exception{
        LocateRegistry.createRegistry(7744);
        IHello rhello = new HelloImpl();
        Naming.bind("rmi://localhost:7744/RHello", rhello);
        System.out.println("java.rmi.start...");

    }
}
