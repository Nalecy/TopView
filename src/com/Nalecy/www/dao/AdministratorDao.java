package com.Nalecy.www.dao;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Person;

public interface AdministratorDao {
    void addAdministrator(Person person);

    Administrator searchAdministrator(String userName);

    Administrator searchAdministrator(Integer id);

    void updateAdministrator(Administrator administrator);
}
