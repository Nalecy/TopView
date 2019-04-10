package com.Nalecy.www.dao;

import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedList;

public class UserDao {
    public static String getPassword(String userName){
        LinkedList<String> password = DatabaseUtil.getInstance().getOneColumnData("user", "password", "username", userName);
        String pw = password.get(0);
        return pw;
    }


    public static void main(String[] args) {
    }
}
