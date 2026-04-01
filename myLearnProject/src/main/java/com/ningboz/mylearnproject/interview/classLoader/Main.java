package com.ningboz.mylearnproject.interview.classLoader;

import com.ningboz.mylearnproject.Point3D;
import com.sun.javafx.util.Logging;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println(System.getProperty("java.class.path"));
        System.out.println("------------------");
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("------------------");

        System.out.println(Main.class.getClassLoader());
        System.out.println(Main.class.getClassLoader().getParent());
        System.out.println(Logging.class.getClassLoader());
        System.out.println(Logging.class.getClassLoader().getParent());
        System.out.println(ArrayList.class.getClassLoader());
        System.out.println(ArrayList.class.getClassLoader().getParent());
    }
}
