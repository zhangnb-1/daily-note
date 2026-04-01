package com.ningboz.mylearnproject.io.zerocopy;

import java.io.*;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.nio.channels.SocketChannel;

public class Main {
    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        Socket socket = new Socket();
        SocketChannel channel = socket.getChannel();
    }
    /**
     *  零拷贝发送文件
     */

    private static void sendFile(InputStream in, OutputStream out,int size) throws IOException {
        // 读进内存，再通过tcp发送
        byte[] bytes = new byte[size];
        in.read(bytes);
        out.write(bytes);
    }

    private static void sendFileZeroCopy(InputStream in, OutputStream out,int size) throws IOException {
        // 读写io之间建立缓存通道

    }
}
