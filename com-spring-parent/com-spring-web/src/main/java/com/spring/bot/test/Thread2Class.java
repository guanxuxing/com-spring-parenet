package com.spring.bot.test;

/**
 * Created by Administrator on 2018-10-17.
 */
public class Thread2Class implements Runnable {

    @Override
    public void run() {
        synchronized (Test.class) {
            System.out.println("----- thread2 ------");
            Test.money = "200";
            Test.threadLocal.set("200");
            System.out.println("thread2.run.info: " + Test.money + ", threadlocal: " + Test.threadLocal.get());
        }
    }
}
