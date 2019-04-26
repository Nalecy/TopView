package com.Nalecy.www.po;

import com.Nalecy.www.constantClass.IsComment;
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
    private Integer balance;
    private IsComment isComment;


    public Order(){
        userName = "";
        date = new Date(0);
        roomPeriod = 0;
        hotelID = 0;
        roomID = 0;
        balance = 0;
        isComment = IsComment.NO;
    }

    public Order(Integer id,String userName, Date date, Integer roomPeriod, Integer hotelID, Integer roomID,Integer balance,IsComment isComment) {
        this.id = id;
        this.userName = userName;
        this.date = date;
        this.roomPeriod = roomPeriod;
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.balance = balance;
        this.isComment = isComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("用户名："+userName+"，预约时间："+date+"预定时间段：");
        if(roomPeriod.equals(RoomPeriod.MORNING))sb.append("早上");
        else if(roomPeriod.equals(RoomPeriod.AFTERNOON))sb.append("下午");
        else if(roomPeriod.equals(RoomPeriod.NIGHT))sb.append("晚上");
        return sb.toString();
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
    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
    public IsComment getIsComment() { return isComment; }
    public void setIsComment(IsComment isComment) { this.isComment = isComment; }
}
