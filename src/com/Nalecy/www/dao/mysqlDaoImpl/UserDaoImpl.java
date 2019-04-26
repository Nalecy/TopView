package com.Nalecy.www.dao.mysqlDaoImpl;

import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.User;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedHashMap;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static UserDaoImpl instance = null;
    public static UserDaoImpl getInstance(){
        if(instance == null)instance = new UserDaoImpl();
        return instance;
    }
    private UserDaoImpl(){}


    public void addUser(User user) {
        DatabaseUtil.addOneRowData("user", null, user.getUserName(), user.getPassword(),"0");
    }

    public User getUser(String userName) {
        User user = new User();
        List<String> userInfo = DatabaseUtil.getOneRowData("user", "userName", userName);
        if (userInfo == null||userInfo.size() == 0) return null;
        user.setId(Integer.valueOf(userInfo.get(0)));
        user.setUserName(userInfo.get(1));
        user.setPassword(userInfo.get(2));
        user.setHasLogin(Integer.valueOf(userInfo.get(3)));
        return user;
    }

    public void deleteUser(String userName) {
        DatabaseUtil.deleteOneRowData("user", "userName", userName);
    }

    public void updatePassword(String userName, String updPassword) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("password", updPassword);
        DatabaseUtil.updateRowsData("user", "userName", userName, lhm);
    }
    public void updateLoginStmt(String userName, Integer loginStmt){  // stmt statement状态
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("hasLogin", String.valueOf(loginStmt));
        DatabaseUtil.updateRowsData("user", "userName", userName, lhm);
    }


}
