package com.spring.bot.thread;

import java.util.Random;

/**
 * Created by Administrator on 2018-09-27. synchronized
 */
public class SycTest {
    public static long num ;

    public static void main(String[] args){
        handler();
       // System.out.println("---- main ------" + Thread.currentThread().getName());
        /*for (int i =0 ; i< 10 ; i ++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                           handler();
                    }
                }).start();
            }*/
    }

    public static void handler (){
        /*System.out.println("-- handler -- " + Thread.currentThread().getName());
        synchronized (SycTest.class) {
            t2();
            t1();
        }*/
        t2();
        t1();
    }

    public static void t1(){
        System.out.println("------ " + Thread.currentThread().getName() + " **** " + num);
    }

    public static void t2 (){
        num =  new Random().nextInt(10000);
        System.out.println(" ############# " + Thread.currentThread().getName() + " ---- " + num );
    }

}
