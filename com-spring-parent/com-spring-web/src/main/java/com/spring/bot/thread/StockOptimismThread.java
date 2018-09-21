package com.spring.bot.thread;

import com.spring.bot.controller.context.ApplicationContextUtil;
import com.spring.bot.dao.StockDao;
import com.spring.bot.dao.StockOrderDao;
import com.spring.bot.entity.Stock;
import com.spring.bot.entity.StockOrder;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2018-09-20.
 * 乐观锁
 */
public class StockOptimismThread implements Runnable {

    @Override
    public void run() {
        Stock stock = checkStock();
        updateCountByOpti(stock);
        createOrder();
    }

    public void createOrder (){
        StockOrderDao stockOrderDao = (StockOrderDao)ApplicationContextUtil.getApplicationContext().getBean("stockOrderDao");
        stockOrderDao.addStockOrder();
    }

    public void updateCountByOpti (Stock stock){
        StockDao stockDao = (StockDao) ApplicationContextUtil.getApplicationContext().getBean("stockDao");
        int key = stockDao.updateCountByOpti(stock);
        if (key == 0 ){
            return;
        }
    }

    public Stock checkStock (){
        StockDao stockDao = (StockDao) ApplicationContextUtil.getApplicationContext().getBean("stockDao");
        Stock stock = stockDao.getOne(1l);
        if (stock.getCount() < 1l ) {
            throw new RuntimeException("no stock");
        }
        return stock;
    }
}
