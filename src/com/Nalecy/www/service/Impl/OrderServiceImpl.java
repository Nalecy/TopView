package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.OrderDao;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.DateUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl  implements OrderService {
    private static OrderServiceImpl instance;
    public static OrderServiceImpl getInstance(){
        if(instance == null)
            instance = new OrderServiceImpl();
        return instance;
    }

    @Override
    public Order getOrderById(Integer id) {
        return OrderDao.getOrder(id);
    }

    @Override
    public List<Order> getCompleteOrder() {
        List<Order> allOrders = OrderDao.getOrderList();
        if (allOrders == null) return null;
        List<Order> orders = new ArrayList<>();
        Date today = DateUtil.getCurrentDate();
        for (Order order : allOrders) {
            if (order.getDate().getTime() <= today.getTime()) orders.add(order);
        }
        return orders;
    }

    @Override
    public List<Order> getCompleteOrder(String userName) {
        List<Order> allCompleteOrders = getCompleteOrder();
        List<Order> orders = new ArrayList<>();
        if (allCompleteOrders != null) {
            for (Order order : allCompleteOrders) {
                if (order.getUserName().equals(userName)) orders.add(order);
            }
        }
        return orders;
    }
    @Override
    public List<Order> getCompleteOrder(Integer hotelId) {
        List<Order> allCompleteOrders = getCompleteOrder();
        List<Order> orders = new ArrayList<>();
        if (allCompleteOrders != null) {
            for (Order order : allCompleteOrders) {
                if (order.getHotelID().equals(hotelId)) orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<Order> getIncompleteOrder(String userName) {
        List<Order> allIncompleteOrders = getIncompleteOrder();
        List<Order> orders = new ArrayList<>();
        if (allIncompleteOrders != null) {
            for (Order order : allIncompleteOrders) {
                if (order.getUserName().equals(userName)) orders.add(order);
            }
        }
        return orders;
    }
    @Override
    public List<Order> getIncompleteOrder(Integer hotelId) {
        List<Order> allIncompleteOrders = getIncompleteOrder();
        List<Order> orders = new ArrayList<>();
        if (allIncompleteOrders != null) {
            for (Order order : allIncompleteOrders) {
                if (order.getHotelID().equals(hotelId)) orders.add(order);
            }
        }
        return orders;
    }

    @Override
    public List<Order> getIncompleteOrder() {
        List<Order> allOrders = OrderDao.getOrderList();
        if (allOrders == null) return null;
        List<Order> orders = new ArrayList<>();
        Date today = DateUtil.getCurrentDate();
        for (Order order : allOrders) {
            if (order.getDate().getTime() > today.getTime()) orders.add(order);
        }
        return orders;
    }

    @Override
    public void cancelOrder(Order order) {
        OrderDao.deleteOrder(order.getId());
    }

    @Override
    public boolean addOrder(Order order) {
        OrderDao.addOrder(order);
        return true;
    }
}
