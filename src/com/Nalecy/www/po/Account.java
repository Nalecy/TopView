package com.Nalecy.www.po;

import java.sql.Date;

public class Account {
    private Integer id;
    private Integer hotelId;
    private Integer customerId;
    private Date date;
    private Integer balance;
    private Integer roomId;
    private Integer roomPeriod;

    public Account(){
        hotelId = 0;
        customerId = 0;
        date = new Date(0);
        balance = 0;
        roomId = 0;
        roomPeriod = 0;
    }

    public Integer getHotelId() { return hotelId; }
    public void setHotelId(Integer hotelId) { this.hotelId = hotelId; }
    public Integer getCustomerId() { return customerId; }
    public void setCustomerId(Integer customerId) { this.customerId = customerId; }
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
    public Integer getRoomPeriod() { return roomPeriod; }
    public void setRoomPeriod(Integer roomPeriod) { this.roomPeriod = roomPeriod; }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}
