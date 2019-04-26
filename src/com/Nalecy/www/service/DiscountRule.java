package com.Nalecy.www.service;

/**
 * 折扣规则接口
 */
public interface DiscountRule {
    /**
     * 传入折扣前的金额，获取折扣后的金额
     * @param balance 折扣前金额
     * @return Integer 折扣后金额
     */
    Integer getDiscountedBalance(Integer balance);
}
