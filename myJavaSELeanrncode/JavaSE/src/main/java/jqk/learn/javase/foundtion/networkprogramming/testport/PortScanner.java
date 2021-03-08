package jqk.learn.javase.foundtion.networkprogramming.testport;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @Author:JQK
 * @Date:2020/12/24 15:23
 * @Package:jqk.learn.javase.foundtion.networkprogramming.testport
 * @ClassName:PortScanner :检测系统端口那些被占用，那些事开放的
 **/

public class PortScanner {
    public static void main(String[] args) {
        portScanner();
    }

    public static void portScanner() {
        //扫描端口
        for (int port = 1; port < 65536; port++) {
            try {
                new ServerSocket(port);
             /*   System.out.println(port + "端口开放");*/
            } catch (IOException e) {
                System.out.println(port + "端口占用");
            }
        }
    }
}
