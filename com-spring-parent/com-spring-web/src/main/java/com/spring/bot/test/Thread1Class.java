package com.spring.bot.test;

/**
 * Created by Administrator on 2018-10-17.
 */
public class Thread1Class implements Runnable {

    @Override
    public void run()  {
        try {
            synchronized (Test.class) {
                System.out.println("----- thread1 ------");
                Test.money = "100";
                Test.threadLocal.set("100");
                Thread.sleep(3000);
                System.out.println("thread1.run.info: " + Test.money + ", threadlocal:" + Test.threadLocal.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
