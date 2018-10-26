package com.spring.bot.process;

import com.spring.bot.entity.Stock;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by Administrator on 2018-10-26.
 */
public class CustomItemProcessor implements ItemProcessor<Stock, Stock> {
    public Stock process(Stock stock) throws Exception {
        System.out.println("processing ... " + stock.toString());
        return stock;
    }
}
