package com.Nalecy.www.po;

public class Customer extends Person{
    private Integer balance;

    public Customer() {
        permission = 2;
    }


    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}
