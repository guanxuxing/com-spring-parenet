package com.spring.bot.shell;

/**
 * Created by Administrator on 2019-01-17.
 * 调用本地 shell脚本
 */
public class ShellLocal {

    public static void main(String[] args) throws Exception{
        String str = "/home/sense/test.sh";
        Process process = Runtime.getRuntime().exec(str);
        process.waitFor();
        System.out.println("----- success ----");
    }
}
