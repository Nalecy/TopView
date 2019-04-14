package com.Nalecy.www.dao;

import com.Nalecy.www.po.User;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class UserDao {

    public static boolean hasUser(String userName) {
        List<String> userList = DatabaseUtil.getOneColumnData("user", "userName");
        assert userList != null;
        for (String s : userList) {
            if (userName.equals(s)) return true;
        }
        return false;
    }

    public static void addUser(User user) {
        DatabaseUtil.addOneRowData("user", null, user.getUserName(), user.getPassword(),"0");
    }

    public static User getUser(String userName) {
        User user = new User();
        LinkedList<String> userInfo = DatabaseUtil.getOneRowData("user", "userName", userName);
        if (userInfo == null||userInfo.size() == 0) return null;
        user.setId(Integer.valueOf(userInfo.get(0)));
        user.setUserName(userInfo.get(1));
        user.setPassword(userInfo.get(2));
        user.setHasLogin(Integer.valueOf(userInfo.get(3)));
        return user;
    }

    public static void deleteUser(String userName) {
        DatabaseUtil.deleteOneRowData("user", "userName", userName);
    }

    public static void updatePassword(String userName, String updPassword) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("password", updPassword);
        DatabaseUtil.updateRowsData("user", "userName", userName, lhm);
    }
    public static void updateLoginStmt(String userName, Integer loginStmt){  // stmt statement状态
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("hasLogin", String.valueOf(loginStmt));
        DatabaseUtil.updateRowsData("user", "userName", userName, lhm);
    }


}
