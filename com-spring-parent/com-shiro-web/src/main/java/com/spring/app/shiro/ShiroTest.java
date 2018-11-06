package com.spring.app.shiro;

import org.apache.commons.io.IOUtils;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2018-11-06.
 *
 * AB6F32F50F17E9313AB13CE8EC632437
 * 2BA1792E80198B86660082867486CCA5
 *
 */
public class ShiroTest {

    public static void main(String[] args) throws Exception{
        String loginUrl = "http://localhost:8080/web/login?userName=gxx&passWord=abc123";
        String listUrl = "http://localhost:8080/web/user/get;jsessionid=2BA1792E80198B86660082867486CCA5?name=gxx";
        String loginOutUrl = "http://localhost:8080/web/loginOut;jsessionid=AB6F32F50F17E9313AB13CE8EC632437";
        URL url = new URL(listUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        String result = IOUtils.toString(httpURLConnection.getInputStream());
        System.out.println("result: " + result);
    }
}
