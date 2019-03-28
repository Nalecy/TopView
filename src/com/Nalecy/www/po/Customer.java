package com.Nalecy.www.po;

public class Customer extends Person{
    private Integer balance;

    public Customer() {
        permission = 1;
    }

    public Customer(String userName, String password, String telephone, String idNumber, Integer balance) {
        super(userName, password, telephone, idNumber);
        this.balance = balance;
        permission = 1;
    }

    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}
