package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.HotelDao;
import com.Nalecy.www.dao.daoFactory.DaoFactory;
import com.Nalecy.www.dao.mysqlDaoImpl.HotelDaoImpl;
import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.HotelService;

import java.util.*;

public class HotelServiceImpl implements HotelService {
    private static HotelServiceImpl instance;
    public static HotelServiceImpl getInstance(){
        if(instance == null)
            instance = new HotelServiceImpl();
        return instance;
    }
    //定义需要引用的Dao类
    private HotelDao hotelDao = DaoFactory.getHotelDao();

    @Override
    public List<Hotel> getHotelList() {
        List<Hotel> hotelList;
        hotelList = hotelDao.getHotelList();
        return hotelList;
    }

    @Override
    public void addHotel(Hotel hotel){
        hotelDao.addHotel(hotel);
    }

    @Override
    public void deleteHotel(Integer id) {
        hotelDao.deleteHotel(id);
    }

    @Override
    public void updateHotel(Hotel hotel){
        hotelDao.updateHotel(hotel);
    }


    @Override
    public Hotel getHotel(Integer hotelID) {
        Hotel hotel;
        hotel = hotelDao.getHotel(hotelID);
        return hotel;
    }

    @Override
    public Hotel getHotel(String name){
        Hotel hotel;
        hotel = hotelDao.getHotel(name);
        return hotel;
    }

    @Override
    public List<Hotel> fuzzySearchHotel(String name) {
        return hotelDao.getHotelList(name);
    }
}
