package com.spring.bot.thread;

import com.spring.bot.entity.UserInfo;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2018-08-28.
 */
public class ThreadTest {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args)  throws Exception{
      /* test();
        Future future = threadPool.submit(new FileThread(null));
        future.get(3, TimeUnit.MINUTES);*/
        contract();
    }

    public static void contract() throws Exception{
        /***
         * new Callable 必须要指定返回值的类型
         */
        Future f1 = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("------ callable1 ------");
                return "callable1";
            }
        });
        Future f2 = threadPool.submit(new Callable<UserInfo>() {
            @Override
            public UserInfo call() throws Exception {
                UserInfo userInfo = new UserInfo();
                userInfo.setName("zs");
                System.out.println("------ callable2 ------");
                return userInfo;
            }
        });
        /***
         * 接收返回值
         * 当Future不获取返回值的时候，线程执行顺序由内存分配
         * 当Future获取返回值的时候，会等待以上线程执行完成再执行下面线程
         * 如果某个线程执行时间长, 可以指定线程超时时间  future.get(3, TimeUnit.MINUTES);
         */
        String info = (String) f1.get();
        UserInfo userInfo = (UserInfo) f2.get();
        System.out.println("---- finsh ----" + info + ", " + userInfo.toString());
    }

    public static void test(){
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(new FileThread(latch)).start();
        new Thread(new ConvertThread(latch)).start();
        try {
            latch.await();  //  使当前线程在锁存器倒计数至零之前一直等待，除非线程被中断。
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------- success -------------" + latch.getCount());
    }
}
