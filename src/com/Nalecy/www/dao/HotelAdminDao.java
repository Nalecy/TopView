package com.Nalecy.www.dao;

import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.po.Person;

public interface HotelAdminDao {
    void addHotelAdmin(Person person);
    HotelAdmin searchHotelAdmin(String userName);
    HotelAdmin searchHotelAdmin(Integer id);
    void updateHotelAdmin(HotelAdmin hotelAdmin);
}
