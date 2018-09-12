package com.spring.bot.thread;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Administrator on 2018-08-28.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ConvertThread implements Runnable {

    private CountDownLatch latch;

    public ConvertThread(CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("--- convert.thread" );
        latch.countDown();  // 递减锁存器的计数，如果计数到达零，则释放所有等待的线程。
    }
}
