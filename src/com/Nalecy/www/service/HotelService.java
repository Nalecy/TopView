package com.Nalecy.www.service;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.Room;

import java.util.*;

public class HotelService {
    private String currentUser = null;
    private Hotel currentHotel = null;
    private static HotelService ourInstance;
    public static HotelService getInstance() {
        if(ourInstance == null)ourInstance = new HotelService();
        return ourInstance;
    }


    private HotelService() {
    }

    public ArrayList<Hotel> getHotelList() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        Hotel hotel = new Hotel(1,"A",4,1.1,1,"6666");
        Hotel hotel2 = new Hotel(2,"B",5,2.2,2,"7777");
        hotelList.add(hotel);
        hotelList.add(hotel2);
        return hotelList;
    }

    public ArrayList< Room> getRoomList() { //测试
        ArrayList< Room> roomList = new ArrayList<>();
        Room room = new Room(1,"中通",1,1,1,1,1);
        Room room2 = new Room(2,"圆通",2,2,2,2,2);
        roomList.add(room);
        roomList.add(room2);
        return roomList;
    }

    public void setCurrentUser(String userName){
        currentUser = userName;
    }
    public String getCurrentUser() {
        return currentUser;
    }
    public Hotel getCurrentHotel() { return currentHotel; }
    public void setCurrentHotel(Hotel currentHotel) { this.currentHotel = currentHotel; }

    public Hotel getHotelById(Integer hotelID) {
        return new Hotel(1,"A",4,1.1,1,"6666");
    }
}
