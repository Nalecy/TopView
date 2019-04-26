package com.Nalecy.www.dao;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Person;

public interface CustomerDao {
    void addCustomer(Person person);
    Customer searchCustomer(String userName);
    Customer searchCustomer(Integer id);
    void updateCustomer(Customer customer);

}
