package com.spring.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrator on 2018-09-20.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class StockOrder {
    private Long id;
    private Long sid;
    private String name;
    private String createTime;
}
