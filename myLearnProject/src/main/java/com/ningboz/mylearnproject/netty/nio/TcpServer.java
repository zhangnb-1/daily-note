package com.ningboz.mylearnproject.netty.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TcpServer {
    public static void main(String[] args) throws IOException, InterruptedException {
//        startSimpleServer();
        testSocketAccept();
    }

    public static void startSimpleServer() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        // -1 退出 0 关闭  其他：发消息
        Socket socket = serverSocket.accept();
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        while(true){
            byte[] bytes = new byte[1024];
            System.out.println("读消息前");
            in.read(bytes);
            System.out.println("读消息后");
            System.out.println(new String(bytes));
            if(socket.isClosed()) {
                continue;
            }
            Scanner sc = new Scanner(System.in);
            String next = sc.next();
            if("-1".equals(next)){
                break;
            }else if ("0".equals(next)){
                socket.close();
            }else{
                out.write(next.getBytes());
            }
        }
    }

    public static void startTcpServer(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        for (int i = 0; i < 3; i++) {
            Socket socket = serverSocket.accept();
            new Thread(()->{
                InputStream in = null;
                OutputStream out = null;
                try {
                    Thread.sleep(1000);
                    in = socket.getInputStream();
                    out = socket.getOutputStream();

                    byte[] bytes = new byte[1024];
                    in.read(bytes);
                    out.write(bytes);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        in.close();
                        out.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    public static void testSocketAccept() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println(1);
        Socket socket = serverSocket.accept();
        System.out.println(2);
        InputStream inputStream = socket.getInputStream();
        System.out.println(3);
        byte[] bytes = new byte[1024];
        inputStream.read(bytes);
        System.out.println(new String(bytes));

        System.out.println(socket.isClosed());
        Thread.sleep(1000);
        System.out.println(socket.isClosed());

    }
}
