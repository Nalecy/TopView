package com.Nalecy.www.service;

import com.Nalecy.www.po.Order;

import java.util.List;

public interface OrderService {

    /**
     * 根据订单id获取订单
     * @param id
     * @return
     */
    Order getOrderById(Integer id);
    /**
     * 获取所有的已完成订单列表
     * @return List<Order>
     */
    List<Order> getCompleteOrder();

    /**
     * 通过用户名获取已完成订单列表
     * @param userName
     * @return List<Order>
     */
    List<Order> getCompleteOrder(String userName);

    /**
     * 通过酒店ID获取已完成订单列表
     * @param hotelId
     * @return List<Order>
     */
    List<Order> getCompleteOrder(Integer hotelId);

    /**
     * 获取所有的未完成订单列表
     * @param userName
     * @return List<Order>
     */
    List<Order> getIncompleteOrder(String userName);

    /**
     * 通过酒店ID获取未完成订单列表
     * @param hotelId
     * @return
     */
    List<Order> getIncompleteOrder(Integer hotelId);

    /**
     * 通过用户名获取未完成订单列表
     * @return
     */
    List<Order> getIncompleteOrder();

    /**
     * 取消订单
     * @param order
     */
    void cancelOrder(Order order);

    /**
     * 添加订单
     * @param order
     * @return
     */
    boolean addOrder(Order order);
}
