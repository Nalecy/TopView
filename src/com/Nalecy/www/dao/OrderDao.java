package com.Nalecy.www.dao;

import com.Nalecy.www.po.Order;

import java.util.List;

public interface OrderDao {
    /**
     * 增加订单
     * @param order 订单对象
     */
    void addOrder(Order order);
    /**
     * 通过订单id获取订单对象
     * @param orderId  订单id
     * @return Order 订单对象
     */
    Order getOrder(Integer orderId);

    /**
     * 获取所有订单列表
     * @return List<Order>
     */
    List<Order> getOrderList();

    /**
     * 根据订单id删除订单
     * @param id 订单id
     */
    void deleteOrder(Integer id);

    /**
     * 更新订单
     * @param order 订单对象
     */
    void updateOrder(Order order);
}
