package com.Nalecy.www.service;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.po.Person;

public class PersonService {
    private static PersonService ourInstance;
    public static PersonService getInstance() {
        if(ourInstance == null)ourInstance = new PersonService();
        return ourInstance;
    }
    private PersonService(){
    }
    public String getPassword(String userName){
        return "123456";
    }
    public Person searchPerson(Integer personId){
        return new Customer();
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
}
