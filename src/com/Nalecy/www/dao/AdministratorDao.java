package com.Nalecy.www.dao;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Person;

public interface AdministratorDao {
    /**
     * 增加超管
     * @param person 超管对象
     */
    void addAdministrator(Person person);

    /**
     * 通过用户名获取超管对象
     * @param userName 用户名
     * @return 超管对象
     */
    Administrator getAdministrator(String userName);

    /**
     * 通过id获取超管对象
     * @param id 超管id
     * @return 超管对象
     */
    Administrator getAdministrator(Integer id);

    /**
     * 更新超管信息
     * @param administrator 超管对象
     */
    void updateAdministrator(Administrator administrator);
}
