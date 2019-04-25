package com.Nalecy.www.service;

import com.Nalecy.www.service.DiscountRule;

class FullHundredMinusThirty implements DiscountRule {
    @Override
    public Integer getDiscountedBalance(Integer balance) {
        if(balance >= 100)balance = balance - 30;
        return balance;
    }
}
