package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.HotelDao;
import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.HotelService;

import java.util.*;

public class HotelServiceImpl implements HotelService {


    @Override
    public List<Hotel> getHotelList() {
        List<Hotel> hotelList;
        hotelList = HotelDao.getHotelList();
        return hotelList;
    }

    @Override
    public void addHotel(Hotel hotel){
        HotelDao.addHotel(hotel);
    }

    @Override
    public void deleteHotel(Integer id) {
        HotelDao.deleteHotel(id);
    }

    @Override
    public void updateHotel(Hotel hotel){
        HotelDao.updateHotel(hotel);
    }


    @Override
    public Hotel getHotel(Integer hotelID) {
        Hotel hotel;
        hotel = HotelDao.getHotel(hotelID);
        return hotel;
    }

    @Override
    public Hotel getHotel(String name){
        Hotel hotel;
        hotel = HotelDao.getHotel(name);
        return hotel;
    }
}
