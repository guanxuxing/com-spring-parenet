package com.spring.bot.job;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2018-08-31.
 */
public class JobTest {

    public static void main(String[] args){
       // JobManager.addJob("clear.file.job", ClearFileJob.class, "*/5 * * * * ?");  // 每五秒执行一次
        JSONObject jsonObject = new JSONObject();
        System.out.println(jsonObject.getString("12"));
    }
}
