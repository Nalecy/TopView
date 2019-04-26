package com.Nalecy.www.dao;

import com.Nalecy.www.po.Hotel;

import java.util.List;

public interface HotelDao {
    /**
     * 增加酒店
     * @param hotel 酒店对象
     */
    void addHotel(Hotel hotel);
    /**
     * 通过酒店名获取酒店对象
     * @param hotelName 酒店名
     * @return 酒店对象
     */
    Hotel getHotel(String hotelName);
    /**
     * 通过id获取酒店
     * @param hotelId 酒店对象id
     * @return 酒店对象
     */
    Hotel getHotel(Integer hotelId);
    /**
     * 获取所有酒店对象的列表
     * @return List<Hotel>
     */
    List<Hotel> getHotelList();
    /**
     * 通过字符串模糊搜索并返回 相关酒店对象的列表
     * @param fuzzyHotelName 模糊的酒店名
     * @return List<Hotel>
     */
    List<Hotel> getHotelList(String fuzzyHotelName);
    /**
     * 根据酒店id删除酒店
     * @param hotelId 酒店id
     */
    void deleteHotel(Integer hotelId);
    /**
     * 更新酒店信息
     * @param hotel 酒店对象
     */
    void updateHotel(Hotel hotel);
}
