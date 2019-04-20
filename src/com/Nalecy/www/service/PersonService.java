package com.Nalecy.www.service;

import com.Nalecy.www.po.Person;

public interface PersonService {
    String getPassword(String userName);
    Person searchPerson(Integer personId);
    Person searchPerson(String userName);
    Integer getPersonID(String userName);
    boolean saveLogin(String userName);
    boolean cancelLogin(String userName);
    boolean hasLogin(String userName);
    boolean addPerson(Person person);
    boolean updatePeron(Person person);

}
