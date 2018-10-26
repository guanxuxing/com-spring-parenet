package com.spring.bot.process;

import com.spring.bot.entity.Stock;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Administrator on 2018-10-26.
 */
public class StockRowMapper implements RowMapper<Stock> {

    public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
        Stock stock = new Stock();
        stock.setId(rs.getLong("id"));
        stock.setName(rs.getString("name"));
        stock.setCount(rs.getLong("count"));
        stock.setSale(rs.getLong("sale"));
        stock.setVersion(rs.getInt("version"));
        return stock;
    }
}
