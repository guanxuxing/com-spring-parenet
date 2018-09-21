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
public class Stock {

    private Long id;
    private String name;
    private long count;
    private long sale;
    private long version;
}
