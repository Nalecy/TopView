package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.AdministratorDao;
import com.Nalecy.www.dao.CustomerDao;
import com.Nalecy.www.dao.HotelAdminDao;
import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.*;
import com.Nalecy.www.service.PersonService;

public class PersonServiceImpl implements PersonService {

    @Override
    public String getPassword(String userName) {
        User user = UserDao.getUser(userName);
        if(user == null)return null;
        return user.getPassword();
    }
    @Override
    public Person searchPerson(Integer personId) {
        Person person = null;
        if ((person = CustomerDao.searchCustomer(personId)) != null) return person;
        if ((person = HotelAdminDao.searchHotelAdmin(personId)) != null) return person;
        if ((person = AdministratorDao.searchAdministrator(personId)) != null) return person;
        return null;
    }
    @Override
    public Person searchPerson(String userName) {
        Person person;
        if ((person = CustomerDao.searchCustomer(userName)) != null) return person;
        if ((person = HotelAdminDao.searchHotelAdmin(userName)) != null) return person;
        if ((person = AdministratorDao.searchAdministrator(userName)) != null) return person;
        return null;
    }
    @Override
    public Integer getPersonID(String userName) {
        return UserDao.getUser(userName).getId();
    }
    @Override
    public boolean saveLogin(String userName) {
        UserDao.updateLoginStmt(userName, 1);
        return true;
    }
    @Override
    public boolean cancelLogin(String userName) {
        UserDao.updateLoginStmt(userName, 0);
        return true;
    }
    @Override
    public boolean hasLogin(String userName) {
        User user = UserDao.getUser(userName);
        if (user == null) return false;
        if (user.getHasLogin() == 1) return true;
        else return false;
    }
    @Override
    public boolean addPerson(Person person) {
        User user = new User();
        Integer id;
        user.setUserName(person.getUserName());
        user.setPassword(person.getPassword());
        UserDao.addUser(user);
        id = UserDao.getUser(person.getUserName()).getId();
        person.setId(id);
        if (person.getPermission() == 1) CustomerDao.addCustomer(person);
        else if (person.getPermission() == 2) HotelAdminDao.addHotelAdmin(person);
        else if (person.getPermission() == 3) AdministratorDao.addAdministrator(person);
        return true;
    }
    @Override
    public boolean updatePeron(Person person) {
        Integer id;
        UserDao.updatePassword(person.getUserName(),person.getPassword());
        id = UserDao.getUser(person.getUserName()).getId();
        person.setId(id);
        if (person.getPermission() == 1) CustomerDao.updateCustomer((Customer) person);
        else if (person.getPermission() == 2) HotelAdminDao.updateHotelAdmin((HotelAdmin) person);
        else if (person.getPermission() == 3) AdministratorDao.updateAdministrator((Administrator) person);
        return true;
    }
}