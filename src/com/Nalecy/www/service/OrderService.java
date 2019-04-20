package com.Nalecy.www.service;

import com.Nalecy.www.po.Order;

import java.util.List;

public interface OrderService {
    List<Order> getCompleteOrder();
    List<Order> getCompleteOrder(String userName);

    List<Order> getCompleteOrder(Integer hotelId);

    List<Order> getIncompleteOrder(String userName);

    List<Order> getIncompleteOrder(Integer hotelId);

    List<Order> getIncompleteOrder();
    void cancelOrder(Order order);
    boolean addOrder(Order order);
}
