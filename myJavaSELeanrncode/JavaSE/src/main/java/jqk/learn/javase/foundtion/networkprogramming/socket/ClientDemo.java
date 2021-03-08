package jqk.learn.javase.foundtion.networkprogramming.socket;

import java.io.*;
import java.net.Socket;

/**
 * @Author:JQK
 * @Date:2020/12/24 11:35
 * @Package:jqk.learn.javase.foundtion.networkprogramming.socket
 * @ClassName:ClientDemo 客户端
 **/

public class ClientDemo {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //一行一行的读
        String tempStr = null;
        while ((tempStr = bufferedReader.readLine()) != null) {
            System.out.println(tempStr);
        }
    }
}
