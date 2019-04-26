package com.Nalecy.www.service.Impl;

import com.Nalecy.www.service.DiscountRule;

/**.
 * 满一百减三十的折扣规则类
 */
public class FullHundredMinusThirty implements DiscountRule {
    @Override
    public Integer getDiscountedBalance(Integer balance) {
        if(balance >= 100)balance = balance - 30;
        return balance;
    }
}
