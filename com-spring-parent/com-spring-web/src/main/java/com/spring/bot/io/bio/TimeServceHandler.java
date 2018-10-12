package com.spring.bot.io.bio;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Administrator on 2018-09-26.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class TimeServceHandler implements Runnable {

    private Socket socket;

    public TimeServceHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out = new PrintWriter(this.socket.getOutputStream(), true);
            String currentTime = null;
            String body = null;
            while (StringUtils.isNotBlank(body = in.readLine())) {
                System.out.println("------  body ---------- " + body);
                System.out.println("the time server receive order : " + body);
                out.println(body + "-- success");
            }
            System.out.println(" ------------------------- ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                /*if (null != in) {
                    in.close();
                    in = null;
                }
                if (null != out) {
                    out.close();
                    out = null;
                }
                if (null != this.socket) {
                    System.out.println("socket server close");
                    this.socket.close();
                    this.socket = null;
                }*/
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
