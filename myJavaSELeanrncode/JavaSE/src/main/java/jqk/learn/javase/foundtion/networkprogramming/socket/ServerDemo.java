package jqk.learn.javase.foundtion.networkprogramming.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author:JQK
 * @Date:2020/12/23 17:52
 * @Package:jqk.learn.javase.foundtion.networkprogramming.socket
 * @ClassName:NetDemo 服务端
 **/

public class ServerDemo {

    public static void main(String[] args) {
        createServer();
    }

    public static void createServer() {
        ServerSocket serverSocket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        PrintWriter printWriter = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(8080);
            //BIO:阻塞IO，等待接收外部的链接，起到监听的作用+-+
            socket = serverSocket.accept();
            //输出流：写数据   第二个参数表示是否刷新true/false
            printWriter = new PrintWriter(socket.getOutputStream(), false);
            printWriter.write("output success");
            //拿到输入流：读数据
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String tempStr = null;
            //一行一行的读，不停的阻塞
            while ((tempStr = bufferedReader.readLine()) != null) {
                System.out.println(tempStr);
            }
            //拿到输出流：写数据
           /* bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedWriter.write("accept html");*/
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
