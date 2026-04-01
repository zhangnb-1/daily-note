package com.ningboz.mylearnproject.netty.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.util.Set;

public class NioTcpServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;

    public void start(int port) throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        // 绑定端口号
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        // 设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//        serverSocketChannel.register(selector, SelectionKey.OP_READ);
//        serverSocketChannel.register(selector, SelectionKey.OP_WRITE);
//        serverSocketChannel.register(selector, SelectionKey.OP_CONNECT);

        // 监听 接收消息
        startListener();
    }

    public void startListener() throws Exception {
        while(true){
            if(selector.select(1000)==0){
                System.out.println("本次监听无消息！");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for (SelectionKey selectionKey : selectionKeys) {
                if(selectionKey.isAcceptable()){
                    handleConnection();
                }
                if(selectionKey.isReadable())
                    handleMsg(selectionKey);
            }


        }
    }

    private void handleMsg(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer attachment = (ByteBuffer) key.attachment();
        channel.read(attachment);
        System.out.println("current msg: " + new String(attachment.array()));
    }

    private void handleConnection() throws Exception {
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
    }

    public static void main(String[] args) throws FileNotFoundException {
        IntBuffer intBuffer = IntBuffer.allocate(1024);
//        FileChannel channel = new FileInputStream("").getChannel();
        for (int i = 0; i < 1024; i++) {
            intBuffer.put(i);
        }
        IntBuffer flip = (IntBuffer) intBuffer.flip();
        for (int val : intBuffer.array()) {
            System.out.println(val);
        }
        System.out.println(intBuffer.get(100));
        System.out.println(flip.get(100));

        intBuffer.clear();
    }
}
