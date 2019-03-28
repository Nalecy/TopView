package com.Nalecy.www.po;

import com.Nalecy.www.constantClass.RoomPeriod;
import com.Nalecy.www.constantClass.RoomType;

import java.sql.Date;

public class Order {
    private Integer id;
    private String userName;
    private Date date;
    private Integer roomPeriod;
    private Integer hotelID;
    private Integer roomID;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("用户名："+userName+"，预约时间："+date+"预定时间段：");
        if(roomPeriod.equals(RoomPeriod.MORRNING))sb.append("早上");
        else if(roomPeriod.equals(RoomPeriod.AFTERNOON))sb.append("下午");
        else if(roomPeriod.equals(RoomPeriod.NIGHT))sb.append("晚上");
        return sb.toString();
    }

    public Order(String userName, Date date, Integer roomPeriod, Integer hotelID, Integer roomID) {
        this.userName = userName;
        this.date = date;
        this.roomPeriod = roomPeriod;
        this.hotelID = hotelID;
        this.roomID = roomID;
    }

    public Order() {
    }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
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
