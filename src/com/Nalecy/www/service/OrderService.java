package com.Nalecy.www.service;

import com.Nalecy.www.dao.OrderDao;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private static OrderService ourInstance;
    public static OrderService getInstance() {
        if(ourInstance == null)ourInstance = new OrderService();
        return ourInstance;
    }
    OrderService() {
    }

    public List<Order> getCompleteOrder() {
        List<Order> allOrders = OrderDao.getOrderList();
        if(allOrders == null)return null;
        List<Order> orders = new ArrayList<>();
        Date today = DateUtil.getInstance().getCurrentDate();
        for (Order order : allOrders) {
            if(order.getDate().getTime() <= today.getTime())
                orders.add(order);
        }
        return orders;
    }
    public List<Order> getCompleteOrder(String userName) {
        List<Order> allCompleteOrders = getCompleteOrder();
        List<Order> orders = new ArrayList<>();
        for (Order order : allCompleteOrders) {
            if(order.getUserName().equals(userName))
                orders.add(order);
        }
        return orders;
    }

    public List<Order> getIncompleteOrder(String userName) {
        List<Order> allIncompleteOrders = getIncompleteOrder();
        List<Order> orders = new ArrayList<>();
        for (Order order : allIncompleteOrders) {
            if(order.getUserName().equals(userName))
                orders.add(order);
        }
        return orders;
    }

    public List<Order> getIncompleteOrder() {
        List<Order> allOrders = OrderDao.getOrderList();
        if(allOrders == null)return null;
        List<Order> orders = new ArrayList<>();
        Date today = DateUtil.getInstance().getCurrentDate();
        for (Order order : allOrders) {
            if(order.getDate().getTime() > today.getTime())
                orders.add(order);
        }
        return orders;
    }

    public void cancelOrder(Order order) {
    }

    public boolean addOrder(Order order) {
        OrderDao.addOrder(order);
        return true;
    }
}
