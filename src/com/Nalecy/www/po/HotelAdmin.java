package com.Nalecy.www.po;

import com.Nalecy.www.constantClass.Permission;

public class HotelAdmin extends Person{
    private Integer hotelID;
    public HotelAdmin(){
        hotelID = 0;
        permission = Permission.HOTELADMIN;
    }



    public Integer getHotelID() { return hotelID; }
    public void setHotelID(Integer hotelID) { this.hotelID = hotelID; }
}
