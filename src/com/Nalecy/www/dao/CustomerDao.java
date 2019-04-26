package com.Nalecy.www.dao;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Person;

public interface CustomerDao {
    /**
     * 增加顾客
     * @param person 顾客对象
     */
    void addCustomer(Person person);
    /**
     * 通过用户名获取顾客对象
     * @param userName 用户名
     * @return 顾客对象
     */
    Customer getCustomer(String userName);
    /**
     * 通过id获取顾客
     * @param id 顾客对象id
     * @return 顾客对象
     */
    Customer getCustomer(Integer id);
    /**
     * 更新顾客信息
     * @param customer 顾客对象
     */
    void updateCustomer(Customer customer);

}
