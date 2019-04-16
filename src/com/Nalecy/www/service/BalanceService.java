package com.Nalecy.www.service;

import com.Nalecy.www.dao.AccountDao;
import com.Nalecy.www.po.Account;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.Person;

public class BalanceService {
    public boolean payment(Order order) {
        //获取价格
        Integer price = RoomService.getInstance().getRoomById(order.getRoomID()).getPrice();
        //获取顾客
        Customer customer = (Customer) PersonService.getInstance().searchPerson(order.getUserName());
        //确保余额充足
        if (customer.getBalance() < price) return false;
        else {
            //扣款
            customer.setBalance(customer.getBalance() - price);
            PersonService.getInstance().updatePeron(customer);
            return true;
        }
    }

    public void monerOut(Order order) {
        //获取用户
        Customer customer = (Customer) PersonService.getInstance().searchPerson(order.getUserName());
        //获取账单
        Account account = AccountDao.getAccountByOrder(order.getId());
        //通过账单金额退款
        customer.setBalance(customer.getBalance() + account.getBalance());
    }
}
