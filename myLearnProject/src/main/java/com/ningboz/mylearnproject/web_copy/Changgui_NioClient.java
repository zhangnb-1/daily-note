package com.ningboz.mylearnproject.web_copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Changgui_NioClient {
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 6666;

    public void start() throws IOException, IOException {
        // 打开 SocketChannel
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        Scanner scanner = new Scanner(System.in);
        System.out.println("输入任意字符启动链接！");
        scanner.next();

        // 连接到服务器
        boolean isConnected = socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));

        if (!isConnected) {
            // 如果连接未立即建立，等待连接完成
            while (!socketChannel.finishConnect()) {
                System.out.println("Connecting to server...");
            }
        }

        System.out.println("Connected to server: " + socketChannel.getRemoteAddress());
        while(true){
            // 发送消息
            System.out.print("发送消息：");
            String message = scanner.next();
            if("1".equals(message))
                break;
            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes(StandardCharsets.UTF_8));
            socketChannel.write(buffer);
            socketChannel.write(new ByteBuffer[]{buffer},0,1024);


            // 读取服务器的响应
            buffer.clear();
            int bytesRead = socketChannel.read(buffer);

            if (bytesRead > 0) {
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                String response = new String(data, StandardCharsets.UTF_8).trim();
                System.out.println("Received response: " + response);
            } else {
                System.out.println("No response received from server.");
            }
        }



        // 关闭连接
        socketChannel.close();
    }

    public static void main(String[] args) {
        try {
            new Changgui_NioClient().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
