package com.Nalecy.www.po;

public class HotelAdmin extends Person{
    private Integer hotelID;
    public HotelAdmin(){
        permission = 2;
    }



    public Integer getHotelID() { return hotelID; }
    public void setHotelID(Integer hotelID) { this.hotelID = hotelID; }
}
