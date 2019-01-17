package com.spring.bot.shell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

/**
 * Created by Administrator on 2019-01-17.
 */
public class ShellRomote {

    public static void main(String[] args) throws Exception{

        // 在某台服务器上执行shell脚本
       // shellCommand();

        //调用指定服务器的shell脚本
        shellCommandSh();

    }

    public static void shellCommand() throws Exception{
        Connection connection = new Connection("192.168.1.102");
        connection.connect();
        connection.authenticateWithPassword("root",  "abc123");
        Session session = connection.openSession();
        session.execCommand("echo '123' >> /home/sense/123.txt");
        System.out.println("--- success ----");
        session.close();
        connection.close();
    }

    public static void shellCommandSh() throws Exception{
        Connection connection = new Connection("192.168.1.102");
        connection.connect();
        connection.authenticateWithPassword("root",  "abc123");
        Session session = connection.openSession();
        session.execCommand("cd /home/sense && ./test.sh");
        System.out.println("--- success ----");
    }
}
