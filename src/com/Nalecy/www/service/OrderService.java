package com.Nalecy.www.service;

import com.Nalecy.www.po.Order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class OrderService {
    private static OrderService ourInstance;

    public static OrderService getInstance() {
        if(ourInstance == null)ourInstance = new OrderService();
        return ourInstance;
    }
    private OrderService() {
    }

    public ArrayList<Order> getCompleteOrder() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(1,"1",new Date(0),1,1,1));
        return orders;
    }

    public ArrayList<Order> getIncompleteOrder() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(new Order(2,"1",new Date(0),1,1,1));
        return orders;
    }

    public void cancelOrder(Order order) {

    }
}
