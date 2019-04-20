package com.Nalecy.www.service;

import com.Nalecy.www.po.Hotel;

import java.util.List;

public interface HotelService {
    List<Hotel> getHotelList();
    void addHotel(Hotel hotel);
    void deleteHotel(Integer id);
    void updateHotel(Hotel hotel);
    void setCurrentUser(String userName);
    String getCurrentUser();
    Hotel getCurrentHotel();
    void setCurrentHotel(Hotel currentHotel);
    Hotel getHotel(Integer hotelID);
    Hotel getHotel(String name);
}
