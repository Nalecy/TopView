package com.Nalecy.www.dao;

import com.Nalecy.www.po.Hotel;

import java.util.List;

public interface HotelDao {
    void addHotel(Hotel hotel);

    Hotel getHotel(String hotelName);

    Hotel getHotel(Integer hotelId);

    List<Hotel> getHotelList();

    List<Hotel> getHotelList(String fuzzyHotelName);

    void deleteHotel(Integer hotelId);

    void updateHotel(Hotel hotel);
}
