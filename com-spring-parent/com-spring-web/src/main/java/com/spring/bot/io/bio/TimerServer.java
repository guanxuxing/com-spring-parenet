package com.spring.bot.io.bio;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018-09-26.
 */
public class TimerServer {

    public static void main(String[] args) throws Exception{
        int port = 7071;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = null;
        while (true) {
            socket = serverSocket.accept();
            new Thread(new TimeServceHandler(socket)).start();
        }
    }
}
