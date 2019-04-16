package com.Nalecy.www.po;

import java.sql.Date;

public class Account {
    private Integer id;
    private Integer orderId;
    private Date date;
    private Integer balance;



    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}
