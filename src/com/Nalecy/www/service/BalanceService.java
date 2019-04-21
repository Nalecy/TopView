package com.Nalecy.www.service;

import com.Nalecy.www.po.Order;

public interface BalanceService {
    /**
     * 根据输入的金额进行充值
     *
     * @param rechargeBalance
     */
    void recharge(Integer rechargeBalance);

    /**
     * 传入订单并生成支付账单存入数据库，返回是否生成成功
     *
     * @param order
     * @return true/false
     */
    boolean payment(Order order);

    /**
     * 传入订单实例并生成退款账单存入数据库
     *
     * @param order
     */
    void refund(Order order);
}
