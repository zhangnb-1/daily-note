package com.ningboz.mylearnproject.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class IoMain {

    public static void main(String[] args) throws IOException {
        // 字节流、字符流
        String filePath = "D:\\personalFile\\fileioLearn\\randomAccessFile.txt";
        String filePath2 = "D:\\personalFile\\fileioLearn\\randomAccessFile2.txt";
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(filePath2);
        FileReader fileReader = new FileReader(filePath);
        fileInputStream.read(new byte[1024]);
        fileReader.read(new char[1024]);

        // 缓冲
        int bufferSize = 1024*1024;
        BufferedInputStream bInput = new BufferedInputStream(fileInputStream,bufferSize);
        BufferedReader bReader = new BufferedReader(fileReader, bufferSize);

        // 阻塞、非阻塞
        // 非阻塞：NIO -> channel、buffer、selector
        FileChannel channel = fileInputStream.getChannel();
        FileChannel channelOut = fileOutputStream.getChannel();
        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, 10);
        MappedByteBuffer mappedByteBuffer2 = channel.map(FileChannel.MapMode.READ_ONLY, 0, 10);

        byte[] array = new byte[10];
        byte[] array2 = new byte[1024];

        mappedByteBuffer.get(array,0,10);
        mappedByteBuffer2.get(array2,0,10);

        System.out.println(new String(array));
        System.out.println(new String(array2));




    }
}
