package com.spring.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrator on 2018-10-25.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class Employ {

    private Long id;
    private String name;
}
