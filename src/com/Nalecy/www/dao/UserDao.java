package com.Nalecy.www.dao;

import com.Nalecy.www.po.User;

public interface UserDao {
    /**
     * 增加用户
     * @param user 用户对象
     */
    void addUser(User user);

    /**
     * 根据用户id获取用户
     * @param id 用户id
     * @return 用户对象
     */
    User getUser(Integer id);
    /**
     * 根据用户名获取用户
     * @param userName 用户名
     * @return 用户对象
     */
    User getUser(String userName);

    /**
     * 根据用户名删除用户
     * @param userName 用户名
     */
    void deleteUser(String userName);

    /**
     * 更新用户信息
     * @param user 用户对象
     */
    void updateUser(User user);

}
