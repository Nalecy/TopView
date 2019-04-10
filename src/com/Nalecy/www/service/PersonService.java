package com.Nalecy.www.service;

import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.*;

public class PersonService {
    private static PersonService ourInstance;
    public static PersonService getInstance() {
        if(ourInstance == null)ourInstance = new PersonService();
        return ourInstance;
    }
    private PersonService(){
    }
    public String getPassword(String userName){
        return UserDao.getPassword(userName);
    }
    public Person searchPerson(Integer personId){
        return new Customer("123","123456","12345678","445281",1);
    }
    public Person searchPerson(String userName){
        return new Customer("123","123456","12345678","445281",1);
    }
    public Integer getPersonID(String userName){
        return null;
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
        return true;
    }
}
