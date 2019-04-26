package com.Nalecy.www.dao.mysqlDaoImpl;

import com.Nalecy.www.dao.CustomerDao;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Person;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static CustomerDaoImpl instance = null;
    public static CustomerDaoImpl getInstance(){
        if(instance == null)instance = new CustomerDaoImpl();
        return instance;
    }
    private CustomerDaoImpl(){}

    public void addCustomer(Person person) {
        Customer customer = (Customer) person;
        DatabaseUtil.addOneRowData("customer", String.valueOf(customer.getId()), customer.getUserName(), customer.getPassword(), customer.getIdNumber(), customer.getTelephone(), String.valueOf(customer.getBalance()),String.valueOf(customer.getIsVip()));
    }

    public Customer getCustomer(String userName) {
        Customer customer = new Customer();
        List<String> list = DatabaseUtil.getOneRowData("customer", "userName", userName);
        if (list == null|| list.size() == 0) return null;
        setInfo(customer, list);
        return customer;
    }

    public Customer getCustomer(Integer id) {
        Customer customer = new Customer();
        List<String> list = DatabaseUtil.getOneRowData("customer", "id", String.valueOf(id));
        if (list == null|| list.size() == 0) return null;
        setInfo(customer, list);
        return customer;
    }

    private void setInfo(Customer customer, List<String> list) {
        customer.setId(Integer.valueOf(list.get(0)));
        customer.setUserName(list.get(1));
        customer.setPassword(list.get(2));
        customer.setIdNumber(list.get(3));
        customer.setTelephone(list.get(4));
        customer.setBalance(Integer.valueOf(list.get(5)));
        customer.setIsVip(Integer.valueOf(list.get(6)));
    }

    public void updateCustomer(Customer customer) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("userName", customer.getUserName());
        lhm.put("password", customer.getPassword());
        lhm.put("idNumber", customer.getIdNumber());
        lhm.put("telephone", customer.getTelephone());
        lhm.put("balance", String.valueOf(customer.getBalance()));
        lhm.put("isVip", String.valueOf(customer.getIsVip()));
        DatabaseUtil.updateRowsData("customer", "id", String.valueOf(customer.getId()), lhm);
    }
}
