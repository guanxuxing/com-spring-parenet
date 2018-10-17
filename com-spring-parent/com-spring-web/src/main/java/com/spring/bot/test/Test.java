package com.spring.bot.test;

/**
 * Created by Administrator on 2018-10-17.
 */
public class Test {

    public static String money = "00";   // 静态资源同步共享

    public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) throws Exception {
        // 两个线程类都要加锁
        new Thread(new Thread1Class()).start();
        new Thread(new Thread2Class()).start();
    }

}
