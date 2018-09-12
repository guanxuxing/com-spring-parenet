package com.spring.bot.entity;

import com.alibaba.fastjson.JSONObject;
import com.spring.bot.controller.base.CommonResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Administrator on 2018-08-27.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = false)
public class UserInfo {

    private String name;
    private String mobile;

    public UserInfo(String name, String mobile){
        this.name = name;
        this.mobile = mobile;
    }

    public UserInfo(String name) {
        this.name = name;
    }

    public UserInfo(){}

    public static void main(String[] args){
        UserInfo userInfo = new UserInfo();
        userInfo.setMobile("zs");
        CommonResponse response = new CommonResponse("E0000", userInfo);
        System.out.println(JSONObject.toJSONString(response));
    }

}
