package com.Nalecy.www.service;

import com.Nalecy.www.dao.HotelDao;
import com.Nalecy.www.dao.RoomDao;
import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.Room;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.*;

public class HotelService {
    private static HotelService ourInstance;
    public static HotelService getInstance() {
        if(ourInstance == null)ourInstance = new HotelService();
        return ourInstance;
    }
    private HotelService() {
    }

    private String currentUser = null;
    private Hotel currentHotel = null;

    public List<Hotel> getHotelList() {
        List<Hotel> hotelList;
        hotelList = HotelDao.getHotelList();
        return hotelList;
    }

    public void addHotel(Hotel hotel){

    }



    public void setCurrentUser(String userName){
        currentUser = userName;
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public Hotel getCurrentHotel() { return currentHotel; }
    public void setCurrentHotel(Hotel currentHotel) { this.currentHotel = currentHotel; }

    public Hotel getHotel(Integer hotelID) {
        Hotel hotel;
        hotel = HotelDao.getHotel(hotelID);
        return hotel;
    }
    public Hotel getHotel(String name){
        Hotel hotel;
        hotel = HotelDao.getHotel(name);
        return hotel;
    }
}
