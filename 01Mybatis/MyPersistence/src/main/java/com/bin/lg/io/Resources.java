package com.bin.lg.io;

import java.io.InputStream;

/**
 * 资源类
 *
 * @author Administrator
 * @date 2021/11/19 15:55
 */
public class Resources {
    /**
     * 读取指定资源的输入流
     * @param path classpath 下的路径
     * @return 输入流
     */
    public static InputStream getResourceAsStream(String path) {
        return Resources.class.getClassLoader().getResourceAsStream(path);
    }
}
