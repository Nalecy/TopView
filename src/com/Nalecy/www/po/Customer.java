package com.Nalecy.www.po;

import com.Nalecy.www.constantClass.Permission;

public class Customer extends Person{
    private Integer balance;
    private Integer isVip;

    public Customer() {
        permission = Permission.CUSTOMER;
        balance = 0;
        isVip = 0;
    }



    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
    public Integer getIsVip() { return isVip; }
    public void setIsVip(Integer isVip) { this.isVip = isVip; }
}
