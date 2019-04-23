package com.Nalecy.www.util;

import com.Nalecy.www.Runner;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ProGetter {
    private static ProGetter ourInstance;
    private static Properties pro;

    public static ProGetter getInstance() {
        if(ourInstance == null)ourInstance = new ProGetter();
        return ourInstance;
    }

    /**
     * 通过固定的路径加载文件
     */
    private ProGetter() {
        pro = new Properties();
        String path = Runner.class.getResource("HotelSystem.properties").getPath();
        try {
            pro.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过key获取对应的值
     * @param key 键名
     * @return String 键值
     */
    public String get(String key){
        return pro.getProperty(key);
    }
}
