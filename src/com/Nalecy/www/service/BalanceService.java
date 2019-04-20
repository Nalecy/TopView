package com.Nalecy.www.service;

import com.Nalecy.www.po.Order;

public interface BalanceService {
    void recharge(Integer number);
    boolean payment(Order order);
    void refund(Order order);
}
