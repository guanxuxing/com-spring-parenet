package com.spring.bot.dao;

import com.spring.bot.entity.Stock;

/**
 * Created by Administrator on 2018-09-20.
 */
public interface StockDao {

    public Stock getOne(Long id);

    public void updateStock(Long id);

    public int updateCountByOpti(Stock stock);
}
