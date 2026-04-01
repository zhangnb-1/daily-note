package com.ningboz.mylearnproject.interview.日常杂技;

import cn.hutool.core.io.checksum.CRC16;

import java.nio.charset.StandardCharsets;

/**
 * @ClassName: Main
 * @author: Znb
 * @date: 2025/9/16
 * @description:
 */
public class Main {
    public static void main(String[] args) {
        int num = -99999;
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(getHighBits(num,3)));
        System.out.println(Integer.toBinaryString(getLowBits(num,3)));
        System.out.println(isZero(num,3));

        String s = "1001";
        String ss = "1002";
        String sss = "1005";
        System.out.println(s.hashCode());
        System.out.println(ss.hashCode());
        System.out.println(sss.hashCode());
        CRC16 crc16 = new CRC16();
        crc16.update(s.getBytes(StandardCharsets.UTF_8),0,s.length());
        System.out.println(crc16.getValue());
        crc16.update(ss.getBytes(StandardCharsets.UTF_8),0,s.length());
        System.out.println(crc16.getValue());
        crc16.update(sss.getBytes(StandardCharsets.UTF_8),0,s.length());
        System.out.println(crc16.getValue());
    }

    // 获取高位
    public static int getHighBits(int num,int n){
        return (num >>> (32 - n)) & ((1 << n) - 1);
    }

    // 获取低位
    public static int getLowBits(int num,int n){
        return num & ((1 << n) - 1);
    }

    // 判断右数第n位是否为0
    public static boolean isZero(int num , int n){
        return (num & (1 << (n - 1))) == 0;
    }

    //一致性哈希
}
