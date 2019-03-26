package com.Nalecy.www.util;

public class DatabaseUtil {
    private static DatabaseUtil ourInstance = new DatabaseUtil();

    public static DatabaseUtil getInstance() {
        return ourInstance;
    }

    private DatabaseUtil() {
    }

}
