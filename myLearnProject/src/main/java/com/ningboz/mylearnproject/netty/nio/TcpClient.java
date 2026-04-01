package com.ningboz.mylearnproject.netty.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TcpClient {
    static Map<String,Socket> connect = new HashMap<>();
    public static void main(String[] args) throws IOException {
//        startSimpleClient();
        testSocket();

    }

    public static void startSimpleClient() throws IOException {
        Socket socket = new Socket("localhost", 6666);
        // -1 退出 0 关闭  其他：发消息
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        while(true){
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if("-1".equals(next)){
                break;
            }else if ("0".equals(next)){
                socket.close();
            }else{
                out.write(next.getBytes());
            }
            byte[] bytes = new byte[1024];
            System.out.println("读消息前");
            in.read(bytes);
            System.out.println("读消息后");
            System.out.println(new String(bytes));
            if(socket.isClosed()) {
                continue;
            }

        }
    }

    public static void startTCPClient() throws IOException {
        Socket socket = new Socket("localhost",6666);

        socket.getOutputStream().write("ceshi".getBytes());
    }

    private static void testSocket() throws IOException {
        Scanner scanner = new Scanner(System.in);
        Socket socket = new Socket("localhost",6666);
        System.out.println("输入任意字符继续..");
        scanner.next();
        OutputStream out = socket.getOutputStream();
        System.out.println("输入任意字符继续..");
        String next = scanner.next();
        out.write(next.getBytes());
    }
}
