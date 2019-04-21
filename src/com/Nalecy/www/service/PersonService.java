package com.Nalecy.www.service;

import com.Nalecy.www.po.Person;

public interface PersonService {
    /**
     * 通过用户名获取密码，若查无用户，应该返回null
     * @param userName
     * @return String
     */
    String getPassword(String userName);

    /**
     * 通过ID获取对应的Customer、HotelAdmin或Administrator
     * @param personId
     * @return Person
     */
    Person searchPerson(Integer personId);

    /**
     * 通过用户名获取对应的Customer、HotelAdmin或Administrator
     * @param userName
     * @return
     */
    Person searchPerson(String userName);

    /**
     * 通过用户名获取对应用户的ID
     * @param userName
     * @return Integer
     */
    Integer getPersonID(String userName);

    /**
     * 传入用户名并保存密码
     * @param userName
     * @return true/false
     */
    boolean saveLogin(String userName);

    /**
     * 取消自动登录,返回是否取消成功
     * @param userName
     * @return true/false
     */
    boolean cancelLogin(String userName);

    /**
     * 判断是否已经保存
     * @param userName
     * @return true/false
     */
    boolean hasLogin(String userName);

    /**
     * 添加一名用户，返回是否添加成功
     * @param person
     * @return true/false
     */
    boolean addPerson(Person person);

    /**
     * 更新一名用户的信息，返回是否添加成功
     * @param person
     * @return true/false
     */
    boolean updatePeron(Person person);

}
