package com.Nalecy.www.service.Impl;

import com.Nalecy.www.constantClass.LoginStatement;
import com.Nalecy.www.constantClass.Permission;
import com.Nalecy.www.dao.AdministratorDao;
import com.Nalecy.www.dao.CustomerDao;
import com.Nalecy.www.dao.HotelAdminDao;
import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.dao.daoFactory.DaoFactory;
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
        if ((person = customerDao.getCustomer(personId)) != null) return person;
        if ((person = hotelAdminDao.getHotelAdmin(personId)) != null) return person;
        if ((person = administratorDao.getAdministrator(personId)) != null) return person;
        return null;
    }
    @Override
    public Person searchPerson(String userName) {
        Person person;
        if ((person = customerDao.getCustomer(userName)) != null) return person;
        if ((person = hotelAdminDao.getHotelAdmin(userName)) != null) return person;
        if ((person = administratorDao.getAdministrator(userName)) != null) return person;
        return null;
    }
    @Override
    public Integer getPersonID(String userName) {
        return userDao.getUser(userName).getId();
    }
    @Override
    public boolean saveLogin(String userName) {
        User user = userDao.getUser(userName);
        user.setHasLogin(LoginStatement.HAS_LOGIN);
        userDao.updateUser(user);
        return true;
    }
    @Override
    public boolean cancelLogin(String userName) {
        User user = userDao.getUser(userName);
        user.setHasLogin(LoginStatement.NO_LOGIN);
        userDao.updateUser(user);
        return true;
    }
    @Override
    public boolean hasLogin(String userName) {
        User user = userDao.getUser(userName);
        if (user == null) return false;
        return user.getHasLogin() == LoginStatement.HAS_LOGIN;
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
        if (person.getPermission() == Permission.CUSTOMER) customerDao.addCustomer(person);
        else if (person.getPermission() == Permission.HOTEL_ADMIN) hotelAdminDao.addHotelAdmin(person);
        else if (person.getPermission() == Permission.ADMINISTRATOR) administratorDao.addAdministrator(person);
        return true;
    }
    @Override
    public boolean updatePeron(Person person) {
        Integer id;
        id = person.getId();
        User user = userDao.getUser(id);
        user.setPassword(person.getPassword());
        userDao.updateUser(user);
        person.setId(id);
        if (person.getPermission() == Permission.CUSTOMER) customerDao.updateCustomer((Customer) person);
        else if (person.getPermission() == Permission.HOTEL_ADMIN) hotelAdminDao.updateHotelAdmin((HotelAdmin) person);
        else if (person.getPermission() == Permission.ADMINISTRATOR) administratorDao.updateAdministrator((Administrator) person);
        return true;
    }
}
