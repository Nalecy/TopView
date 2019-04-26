package com.Nalecy.www.dao;

import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.po.Person;

public interface HotelAdminDao {
    /**
     * 增加酒管
     * @param person 酒管对象
     */
    void addHotelAdmin(Person person);
    /**
     * 通过用户名获取酒管对象
     * @param userName 用户名
     * @return 酒管对象
     */
    HotelAdmin getHotelAdmin(String userName);
    /**
     * 通过id获取酒管
     * @param id 酒管对象id
     * @return 酒管对象
     */
    HotelAdmin getHotelAdmin(Integer id);
    /**
     * 更新酒管信息
     * @param hotelAdmin 酒管对象
     */
    void updateHotelAdmin(HotelAdmin hotelAdmin);
}
