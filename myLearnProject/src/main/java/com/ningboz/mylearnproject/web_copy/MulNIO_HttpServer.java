package com.ningboz.mylearnproject.web_copy;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.concurrent.*;
public class MulNIO_HttpServer {
    private static final int PORT = 6666;

    public static void main(String[] args) {
        try {
            NioMainReactor NIOServer = new NioMainReactor(PORT);
            NIOServer.main_selectLoop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
