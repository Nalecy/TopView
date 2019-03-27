package com.Nalecy.www.service;

public class OrderService {
    private static OrderService ourInstance;

    public static OrderService getInstance() {
        if(ourInstance == null)ourInstance = new OrderService();
        return ourInstance;
    }
    private OrderService() {
    }
}
