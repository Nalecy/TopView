package com.Nalecy.www.service;

import com.Nalecy.www.dao.AdministratorDao;
import com.Nalecy.www.dao.CustomerDao;
import com.Nalecy.www.dao.HotelAdminDao;
import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.*;
import com.Nalecy.www.util.DatabaseUtil;

public class PersonService {
    private static PersonService ourInstance;
    public static PersonService getInstance() {
        if(ourInstance == null)ourInstance = new PersonService();
        return ourInstance;
    }
    private PersonService(){
    }
    public String getPassword(String userName){
        return UserDao.getUser(userName).getPassword();
    }
    public Person searchPerson(Integer personId){
        return new HotelAdmin("123","123456","12345678","445281",1);
    }
    public Person searchPerson(String userName){
        return new HotelAdmin("123","123456","12345678","445281",1);
    }
    public Integer getPersonID(String userName){
        return UserDao.getUser(userName).getId();
    }

    public boolean saveLogin(String userName) {
        Integer id = getPersonID(userName);
        //save(id, userName);
        return true;
    }
    public boolean cancelLogin(String userName){
        //delete(userName);
        return true;
    }
    public boolean hasLogin(String userName) {
        //判断是否保存
        return false;
    }

    public boolean savePersonInfo(Person person) {
        User user = new User();
        Integer id;
        user.setUserName(person.getUserName());
        user.setPassword(person.getPassword());
        UserDao.addUser(user);
        id = UserDao.getUser(person.getUserName()).getId();
        person.setId(id);
        if (person.getPermission() == 1) CustomerDao.addCustomer(person);
        else if(person.getPermission() == 2) HotelAdminDao.addHotelAdmin(person);
        else if(person.getPermission() == 3) AdministratorDao.addAdministrator(person);
        return true;
    }
}
