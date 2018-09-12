package com.spring.bot.controller.base;

import com.github.pagehelper.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrator on 2018-08-31.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class CommonRequest {
    private Page page;
}
