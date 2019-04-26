package com.Nalecy.www.dao;

import com.Nalecy.www.po.Order;

import java.util.List;

public interface OrderDao {
    void addOrder(Order order);

    Order getOrder(String userName);

    Order getOrder(Integer orderId);

    List<Order> getOrderList();

    void deleteOrder(Integer id);

    void updateOrder(Order order);
}
