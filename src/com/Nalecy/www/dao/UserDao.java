package com.Nalecy.www.dao;

import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedList;

public class UserDao {
    public static String getPassword(String userName){
        LinkedList<String> password = DatabaseUtil.getInstance().getOneColumnData("user","password","username",userName);
        if(password == null)return null;
        return password.get(0);
    }
}
