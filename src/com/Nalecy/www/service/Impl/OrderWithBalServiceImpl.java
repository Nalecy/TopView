package com.Nalecy.www.service.Impl;

import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.BalanceService;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.ServiceFactory;

import java.math.BigDecimal;

public class OrderWithBalServiceImpl extends OrderServiceImpl implements OrderService {
    private BalanceService balanceService;
    private boolean hasInit = false;

    private void initService() {
        if (!hasInit) {
            balanceService = ServiceFactory.getBalanceService();
            hasInit = true;
        }
    }



    @Override
    public void cancelOrder(Order order) {
        initService();
        balanceService.refund(order);
        super.cancelOrder(order);

    }

    @Override
    public boolean addOrder(Order order) {
        initService();
        if (balanceService.payment(order)){
            super.addOrder(order);
            return true;
        } else{
          return false;
        }
    }

/*
//获取价格
        Integer price = roomService.getRoomById(order.getRoomID()).getPrice();
        //获取顾客
        Customer customer = (Customer) personService.searchPerson(order.getUserName());
        //获取折扣后价格
        Integer dcPrice = discount.multiply(BigDecimal.valueOf(price)).intValue();
        //确保余额充足
        if (customer.getBalance() < dcPrice) return false;
        else {
            //扣款
            customer.setBalance(customer.getBalance() - price);
            personService.updatePeron(customer);
            super.addOrder(order);
            addAccount(order);
            return true;
        }
 */
}
