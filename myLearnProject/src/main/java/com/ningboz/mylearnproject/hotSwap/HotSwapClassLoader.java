package com.ningboz.mylearnproject.hotSwap;

import java.io.*;

public class HotSwapClassLoader extends ClassLoader {
    private String classPath;

    public HotSwapClassLoader(String classPath) {
        super(null); // 设置父类加载器为null，打破双亲委派
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = loadClassBytes(name);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name);
        }
    }

    private byte[] loadClassBytes(String className) throws IOException {
        String path = classPath + File.separator + className.replace('.', File.separatorChar) + ".class";
        try (InputStream is = new FileInputStream(path);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
            }
            return os.toByteArray();
        }
    }
}
