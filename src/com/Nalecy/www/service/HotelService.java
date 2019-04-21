package com.Nalecy.www.service;

import com.Nalecy.www.po.Hotel;

import java.util.List;

public interface HotelService {
    /**
     * 获取酒店列表
     * @return List<Hotel>
     */
    List<Hotel> getHotelList();

    /**
     * 添加一个酒店
     * @param hotel
     */
    void addHotel(Hotel hotel);

    /**
     *根据酒店的id删除酒店
     * @param id
     */
    void deleteHotel(Integer id);

    /**
     * 更新一个酒店的信息
     * @param hotel
     */
    void updateHotel(Hotel hotel);

    /**
     * 通过酒店id获取酒店
     * @param hotelID
     * @return Hotel
     */
    Hotel getHotel(Integer hotelID);

    /**
     * 通过酒店名字获取酒店
     * @param name
     * @return Hotel
     */
    Hotel getHotel(String name);
}
