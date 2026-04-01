package com.ningboz.mylearnproject.web_copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Changgui_NioServer {
    private static final String INDEX_HTML_RESPONSE = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\nContent-Length: 70\r\n\r\n<head><link rel=\"icon\" href=\"data:;base64,=\"></head>10214304412 袁凡";

    private static final int PORT = 6666;
    private Selector selector;

    public void start() throws IOException {
        // 打开选择器 创建一个 epoll 维护的文件描述符的链表（底层其实是红黑树）
        selector = Selector.open();

        // 打开服务器套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        // 绑定端口
        serverSocketChannel.bind(new InetSocketAddress(PORT));

        //注册选择器 将serverSocket监听到的 accept（新建连接）请求的文件描述符都通过回调函数 添加到selector关联的链表中
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("NIO TCP Server started on port " + PORT);

        while (true){
            // 阻塞
            selector.select(1000);

            Iterator<SelectionKey> selectKeysIterator = selector.selectedKeys().iterator();
            if(!selectKeysIterator.hasNext())
                continue;
            while (selectKeysIterator.hasNext()) {
                SelectionKey selectionKey = selectKeysIterator.next();

                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }

                if(selectionKey.isReadable()){
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int bytesRead = socketChannel.read(buffer);

                    if (bytesRead == -1) {
                        // 连接关闭
                        socketChannel.close();
                        continue;
                    }

                    // 将缓冲区切换为读模式
                    buffer.flip();

                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);

                    String message = new String(data).trim();
                    System.out.println("Received message: " + message);

                    // 回显消息
                    ByteBuffer outBuffer = ByteBuffer.wrap(INDEX_HTML_RESPONSE.getBytes());
                    socketChannel.write(outBuffer);
                }

                selectKeysIterator.remove();
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new Changgui_NioServer().start();
    }
}
