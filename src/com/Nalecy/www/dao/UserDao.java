package com.Nalecy.www.dao;

import com.Nalecy.www.po.User;

public interface UserDao {
    void addUser(User user);
    User getUser(String userName);
    void deleteUser(String userName);
    void updatePassword(String userName, String updPassword);
    void updateLoginStmt(String userName, Integer loginStmt);
}
