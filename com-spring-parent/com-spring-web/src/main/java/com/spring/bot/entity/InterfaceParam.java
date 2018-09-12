package com.spring.bot.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * Created by Administrator on 2018-06-15.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class InterfaceParam {

    private Long id;
    private String callee;
    private String code;
    private String calleeDesc;
    private String filePath;
    private String defaultPlayWav;
    private Boolean uuiFlag;
    private Boolean isBreak;
    private Long addTime;
    private Long updateTime;
    private Long addUser;
    private Long updateUser;

}
