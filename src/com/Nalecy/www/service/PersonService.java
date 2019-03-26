package com.Nalecy.www.service;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.po.Person;

public class PersonService {
    public static String getPassword(String userName){
        return "123456";
    }
    public static Person searchPerson(Integer personId){
        return new Customer();
    }
    public static Integer getPersonID(String userName){
        return null;
    }

    public static boolean saveUser(String userName) {
        Integer id = getPersonID(userName);
        //save(id, userName);
        return true;
    }
    public static boolean hasLogin(String userName) {
        //判断是否保存
        return false;
    }
}
