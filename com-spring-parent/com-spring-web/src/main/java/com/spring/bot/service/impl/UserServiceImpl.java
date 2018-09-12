package com.spring.bot.service.impl;

import com.spring.bot.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-08-24.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getInfo() {
        return "success";
    }
}
