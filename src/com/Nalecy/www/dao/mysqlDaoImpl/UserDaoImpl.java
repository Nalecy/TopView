package com.Nalecy.www.dao.mysqlDaoImpl;

import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.User;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.HashMap;
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

    @Override
    public User getUser(Integer id) {
        User user = new User();
        List<String> userInfo = DatabaseUtil.getOneRowData("user", "id", String.valueOf(id));
        if (userInfo == null||userInfo.size() == 0) return null;
        user.setId(Integer.valueOf(userInfo.get(0)));
        user.setUserName(userInfo.get(1));
        user.setPassword(userInfo.get(2));
        user.setHasLogin(Integer.valueOf(userInfo.get(3)));
        return user;
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

    @Override
    public void updateUser(User user) {
        HashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("password", user.getPassword());
        lhm.put("hasLogin",String.valueOf(user.getHasLogin()));
        DatabaseUtil.updateRowsData("user", "id", String.valueOf(user.getId()), lhm);
    }

}
