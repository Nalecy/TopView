package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.AdministratorDao;
import com.Nalecy.www.dao.CustomerDao;
import com.Nalecy.www.dao.HotelAdminDao;
import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.dao.daoFactory.DaoFactory;
import com.Nalecy.www.dao.mysqlDaoImpl.UserDaoImpl;
import com.Nalecy.www.po.*;
import com.Nalecy.www.service.PersonService;

public class PersonServiceImpl implements PersonService {
    private static PersonServiceImpl instance;
    public static PersonServiceImpl getInstance(){
        if(instance == null)
            instance = new PersonServiceImpl();
        return instance;
    }
    //定义需要引用的Dao类
    private AdministratorDao administratorDao = DaoFactory.getAdministratorDao();
    private CustomerDao customerDao = DaoFactory.getCustomerDao();
    private HotelAdminDao hotelAdminDao = DaoFactory.getHotelAdminDao();
    private UserDao userDao = DaoFactory.getUserDao();

    @Override
    public String getPassword(String userName) {
        User user = userDao.getUser(userName);
        if(user == null)return null;
        return user.getPassword();
    }
    @Override
    public Person searchPerson(Integer personId) {
        Person person = null;
        if ((person = customerDao.searchCustomer(personId)) != null) return person;
        if ((person = hotelAdminDao.searchHotelAdmin(personId)) != null) return person;
        if ((person = administratorDao.searchAdministrator(personId)) != null) return person;
        return null;
    }
    @Override
    public Person searchPerson(String userName) {
        Person person;
        if ((person = customerDao.searchCustomer(userName)) != null) return person;
        if ((person = hotelAdminDao.searchHotelAdmin(userName)) != null) return person;
        if ((person = administratorDao.searchAdministrator(userName)) != null) return person;
        return null;
    }
    @Override
    public Integer getPersonID(String userName) {
        return userDao.getUser(userName).getId();
    }
    @Override
    public boolean saveLogin(String userName) {
        userDao.updateLoginStmt(userName, 1);
        return true;
    }
    @Override
    public boolean cancelLogin(String userName) {
        userDao.updateLoginStmt(userName, 0);
        return true;
    }
    @Override
    public boolean hasLogin(String userName) {
        User user = userDao.getUser(userName);
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
        userDao.addUser(user);
        id = userDao.getUser(person.getUserName()).getId();
        person.setId(id);
        if (person.getPermission() == 1) customerDao.addCustomer(person);
        else if (person.getPermission() == 2) hotelAdminDao.addHotelAdmin(person);
        else if (person.getPermission() == 3) administratorDao.addAdministrator(person);
        return true;
    }
    @Override
    public boolean updatePeron(Person person) {
        Integer id;
        userDao.updatePassword(person.getUserName(),person.getPassword());
        id = userDao.getUser(person.getUserName()).getId();
        person.setId(id);
        if (person.getPermission() == 1) customerDao.updateCustomer((Customer) person);
        else if (person.getPermission() == 2) hotelAdminDao.updateHotelAdmin((HotelAdmin) person);
        else if (person.getPermission() == 3) administratorDao.updateAdministrator((Administrator) person);
        return true;
    }
}
