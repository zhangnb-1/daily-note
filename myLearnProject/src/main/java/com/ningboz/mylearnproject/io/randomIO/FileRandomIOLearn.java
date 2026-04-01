package com.ningboz.mylearnproject.io.randomIO;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FileRandomIOLearn {
    public static Map<String,RandomAccessFile> map = new HashMap<>();
    public static Map<String,BufferedOutputStream> buffermap = new HashMap<>();
    public static void main(String[] args) throws IOException {
        String logFile = "D:\\personalFile\\fileioLearn\\logFile.txt";
        long start = System.currentTimeMillis();
        long times = 0;
        for (long i = 0; i < 1000; i = System.currentTimeMillis()-start) {
            String logMsg = String.format("%s:第%d条日志\n",LocalTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME),times);
            addLogBuffer(logFile,logMsg);
            times++;
        }
        close(logFile);
        System.out.println(String.format("1秒内记录了%s条日志",times));

//        fileRandomTest();
    }

    public static void fileRandomTest() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\personalFile\\fileioLearn\\randomAccessFile.txt", "rwd");
        System.out.println(randomAccessFile.getFilePointer());
        System.out.println(randomAccessFile.length());
        randomAccessFile.seek(2);
        randomAccessFile.write("我".getBytes("GBK"));
        System.out.println(randomAccessFile.getFilePointer());
        System.out.println(randomAccessFile.length());
    }

    // 尾部追加数据 记日志的方式
    public static void addLog(String fileName,String logMsg){
        RandomAccessFile randomAccessFile = null;
        try {
            if(!map.containsKey(fileName))
                map.put(fileName,new RandomAccessFile(fileName,"rw"));
            randomAccessFile = map.get(fileName);
            randomAccessFile.seek(randomAccessFile.length());
            randomAccessFile.write(logMsg.getBytes("UTF-8"));

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addLogBuffer(String fileName,String logMsg){
        BufferedOutputStream out = null;
        try {
            if(!buffermap.containsKey(fileName))
                buffermap.put(fileName,new BufferedOutputStream(new FileOutputStream(fileName,true),1024*1024*100));
            out = buffermap.get(fileName);
            out.write(logMsg.getBytes("UTF-8"));

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeRandomIO(String fileName){
        if(map.containsKey(fileName)){
            RandomAccessFile randomAccessFile = map.remove(fileName);
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void close(String fileName){
        if(buffermap.containsKey(fileName)){
            BufferedOutputStream out = buffermap.remove(fileName);
            try {
                out.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
