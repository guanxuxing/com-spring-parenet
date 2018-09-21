package com.spring.bot.biz.impl;

import com.alibaba.fastjson.JSON;
import com.spring.bot.biz.StockBiz;
import com.spring.bot.dao.StockDao;
import com.spring.bot.dao.StockOrderDao;
import com.spring.bot.entity.Stock;
import com.spring.bot.thread.StockOptimismThread;
import com.spring.bot.thread.StockThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2018-09-20.
 */
@Slf4j
@Service
public class StockBizImpl implements StockBiz {

    @Resource
    private StockOrderDao stockOrderDao;

    @Resource
    private StockDao stockDao;


    @Override
    public void service() {
       /* Stock s1 = stockDao.getOne(1l);
        Stock s2 = stockDao.getOne(1l);

        System.out.println("--------------s1 " + JSON.toJSONString(s1));
        System.out.println("--------------s2 " + JSON.toJSONString(s2));

        int u1 = stockDao.updateCountByOpti(s1);
        System.out.println("修改信息s1: " + (u1==1?"成功":"失败"));

        int u2 = stockDao.updateCountByOpti(s2);
        System.out.println("修改信息s2: " + (u2==1?"成功":"失败"));*/

        //乐观锁   一百个线程模拟抢单
        for (int i=0 ; i<100; i++) {
        new Thread(new StockThread(i)).start();
        }
    }

    // 乐观锁 主要逻辑代码
    public void optimismService(int i){
        log.info("--------------------------- " + (i+1));
        // 查询1号商品
        Stock stock = stockDao.getOne(1l);
        log.info("******* stock.count: " + stock.getCount());
        //商品数量小于1时, 退出方法
        if (stock.getCount() < 1l) {
            log.info("stock.count.is.null ---------------- " + (i+1));
            return;
        }
        // 根据version 更新库存数量
        int key = stockDao.updateCountByOpti(stock);
        // 更新库存数量成功时，添加订单信息
        if (key == 1) {
            log.info("###### add.stock.order  ---------------- " + (i+1));
            stockOrderDao.addStockOrder();
        }
    }

}
