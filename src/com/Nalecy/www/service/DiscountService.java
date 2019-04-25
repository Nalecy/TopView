package com.Nalecy.www.service;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.Person;
import com.Nalecy.www.util.ServiceFactory;

public class DiscountService {
    private static DiscountService instance = null;
    public static DiscountService getInstance() {
        if(instance == null)instance = new DiscountService();
        return instance;
    }
    private boolean hasInit = false;
    private void initService() {
        if (!hasInit) {
            discountRule = new FullHundredMinusThirty();  //初始化折扣规则
            personService = ServiceFactory.getPersonService();
            hasInit = true;
        }
    }

    private DiscountRule discountRule;
    private PersonService personService;

    public Order discountOrder(Order order){
        initService();
        if(isVip(order.getUserName()))
          order.setBalance(discountRule.getDiscountedBalance(order.getBalance()));
        return order;
    }
    public boolean isVip(String userName){
        initService();
        Person person = personService.searchPerson(userName);
        if(person instanceof Customer) {
            Customer customer = (Customer) person;
            return customer.getIsVip().equals(1);
        }
        else {
            throw new RuntimeException("传入isVip的用户名无效,必须为Customer的实例");
        }
    }
    public boolean toBeVip(String userName){
        initService();
        Person person = personService.searchPerson(userName);
        if(person instanceof Customer){
            Customer customer = (Customer) person;
            customer.setIsVip(1);
            personService.updatePeron(customer);
            return true;
        }else {
            throw new RuntimeException("传入toBeVip的用户名无效,必须为Customer的实例");
        }
    }


}
