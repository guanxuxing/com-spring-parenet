package com.spring.bot.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Administrator on 2018-08-31.
 */
public class ResouceUtil {
    private static Properties p = null;

    public ResouceUtil(String path) {
        if(p == null) {
            p = new Properties();
        }

        try {
            p.load(ResouceUtil.class.getClassLoader().getResourceAsStream(path));
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public static String getValue(String key) {
        return p.getProperty(key);
    }

    public static void main(String[] args){
        ResouceUtil resouceUtil = new ResouceUtil("dbconfig.properties");
        System.out.println(ResouceUtil.getValue("url"));
    }
}
