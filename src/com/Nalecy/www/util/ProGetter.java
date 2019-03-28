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

    private ProGetter() {
        pro = new Properties();
        String path = Runner.class.getResource("HotelSystem.properties").getPath();
        try {
            pro.load(new FileReader(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String get(String key){
        return pro.getProperty(key);
    }
}
