package com.Nalecy.www.service;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Order;

import java.math.BigDecimal;

public class OrderWithBalService extends OrderService {
    private static final OrderWithBalService Instance = new OrderWithBalService();
    private OrderWithBalService(){}
    public static OrderWithBalService getInstance(){
        return Instance;
    }

    private BigDecimal discount = BigDecimal.valueOf(1);

    @Override
    public void cancelOrder(Order order) {
        super.cancelOrder(order);

    }

    @Override
    public boolean addOrder(Order order) {
        //获取价格
        Integer price = RoomService.getInstance().getRoomById(order.getRoomID()).getPrice();
        //获取顾客
        Customer customer = (Customer) PersonService.getInstance().searchPerson(order.getUserName());
        //获取折扣后价格
        Integer dcPrice = discount.multiply(BigDecimal.valueOf(price)).intValue();
        //确保余额充足
        if (customer.getBalance() < dcPrice) return false;
        else {
            //扣款
            customer.setBalance(customer.getBalance() - price);
            PersonService.getInstance().updatePeron(customer);
            super.addOrder(order);
            return true;
        }
    }
}
