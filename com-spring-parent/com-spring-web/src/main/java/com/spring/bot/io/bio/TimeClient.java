package com.spring.bot.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2018-09-26.
 */
public class TimeClient {

    public static void main(String[] args){

        int port = 7092;
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket("127.0.01", port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("query time order \n 123");
            String resp = in.readLine();
            System.out.println("Now is :"  + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != in) {
                    in.close();
                    in = null;
                }
                if (null != out) {
                    out.close();
                    out = null;
                }
                if (null != socket) {
                    socket.close();
                    socket = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
