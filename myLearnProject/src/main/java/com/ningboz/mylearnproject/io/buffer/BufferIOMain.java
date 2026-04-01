package com.ningboz.mylearnproject.io.buffer;

import java.io.*;

public class BufferIOMain {
    public static void main(String[] args) {

    }

    private static void testBufferIn() throws IOException {
        String filePath = "";
        int bufferSize = 1024*1024*10;
        BufferedInputStream bIn = new BufferedInputStream(new FileInputStream(filePath),bufferSize);
        byte[] bytes = new byte[1024*1024];
        bIn.read(bytes);
        bIn.read(bytes,0,1024);

    }

    private static void testBufferOut() throws IOException {
        String filePath = "";
        int bufferSize = 1024*1024*10;
        BufferedOutputStream bOut = new BufferedOutputStream(new FileOutputStream(filePath,true),bufferSize);
        bOut.write("".getBytes());
    }
}
