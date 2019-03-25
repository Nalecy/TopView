package com.Nalecy.www.po;

import java.util.Date;

public class Order {
    private Integer id;
    private Date date;
    private Integer roomPeriod;
    private Integer hotelID;
    private Integer roomID;




    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
    public Integer getRoomPeriod() { return roomPeriod; }
    public void setRoomPeriod(Integer roomPeriod) { this.roomPeriod = roomPeriod; }
    public Integer getHotelID() { return hotelID; }
    public void setHotelID(Integer hotelID) { this.hotelID = hotelID; }
    public Integer getRoomID() { return roomID; }
    public void setRoomID(Integer roomID) { this.roomID = roomID; }
}
