package com.spring.bot.thread;

import com.spring.bot.biz.StockBiz;
import com.spring.bot.controller.context.ApplicationContextUtil;
import com.spring.bot.dao.StockDao;
import com.spring.bot.dao.StockOrderDao;
import com.spring.bot.entity.Stock;
import lombok.Data;

import java.util.Date;

/**
 * Created by Administrator on 2018-09-20.
 * 无锁进行商品抢购
 * optimism(乐观)
 * pessimistic(悲观)
 */
@Data
public class StockThread implements Runnable {

    private int num;

    public StockThread (int num) {
        this.num = num;
    }

    @Override
    public synchronized void run() {
        StockBiz stockBiz = (StockBiz) ApplicationContextUtil.getApplicationContext().getBean("stockBizImpl");
        stockBiz.optimismService(num);
    }

    public void service (){
        StockDao stockDao = (StockDao) ApplicationContextUtil.getApplicationContext().getBean("stockDao");
        StockOrderDao stockOrderDao = (StockOrderDao) ApplicationContextUtil.getApplicationContext().getBean("stockOrderDao");
        Stock stock = stockDao.getOne(1l);
        if (stock.getCount() < 1l) {
            return;
        }
        stockDao.updateStock(1l);
        stockOrderDao.addStockOrder();

    }
}
