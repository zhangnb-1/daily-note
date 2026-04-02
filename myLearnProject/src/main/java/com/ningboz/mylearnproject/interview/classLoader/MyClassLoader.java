package com.ningboz.mylearnproject.interview.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader{

    // 类加载器的搜索范围
    private String classPath; // 类文件路径

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = loadClassData(name);
        Class clazz = System.class;
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadClassData(String className) {
        String path = classPath + File.separatorChar + className.replace('.', File.separatorChar) + ".class";
        try (FileInputStream fis = new FileInputStream(path)) {
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
