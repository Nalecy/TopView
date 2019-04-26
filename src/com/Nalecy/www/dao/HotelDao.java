package com.Nalecy.www.dao;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class HotelDao {
    public static void addHotel(Hotel hotel) {
        DatabaseUtil.addOneRowData("hotel", null, hotel.getName(), String.valueOf(hotel.getStar()), String.valueOf(hotel.getScore()), String.valueOf(hotel.getNumOfScore()), hotel.getDescription());
    }

    public static Hotel getHotel(String hotelName) {
        Hotel hotel = new Hotel();
        List<String> userInfo = DatabaseUtil.getOneRowData("hotel", "name", hotelName);
        if (userInfo == null) return null;
        setInfo(hotel, userInfo);
        return hotel;
    }

    public static Hotel getHotel(Integer hotelId) {
        Hotel hotel = new Hotel();
        List<String> userInfo = DatabaseUtil.getOneRowData("hotel", "id", String.valueOf(hotelId));
        if (userInfo == null) return null;
        setInfo(hotel, userInfo);
        return hotel;
    }

    public static List<Hotel> getHotelList() {
        List<String> idList = DatabaseUtil.getOneColumnData("hotel", "id");
        List<Hotel> hotels = new ArrayList<>();
        if (idList == null) return null;
        for (String s : idList) {
            hotels.add(getHotel(Integer.valueOf(s)));
        }
        return hotels;
    }
    public static List<Hotel> getHotelList(String fuzzyHotelName){
        List<String> idList = DatabaseUtil.getOneColumnData("hotel", "id","name","%"+fuzzyHotelName+"%");
        List<Hotel> hotels = new ArrayList<>();
        if (idList == null) return null;
        for (String s : idList) {
            hotels.add(getHotel(Integer.valueOf(s)));
        }
        return hotels;
    }

    private static void setInfo(Hotel hotel, List<String> userInfo) {
        hotel.setId(Integer.valueOf(userInfo.get(0)));
        hotel.setName(userInfo.get(1));
        hotel.setStar(Integer.valueOf(userInfo.get(2)));
        hotel.setScore(Double.valueOf(userInfo.get(3)));
        hotel.setNumOfScore(Integer.valueOf(userInfo.get(4)));
        hotel.setDescription(userInfo.get(5));
    }

    public static void deleteHotel(Integer hotelId) {
        DatabaseUtil.deleteOneRowData("hotel", "id", String.valueOf(hotelId));
    }

    public static void updateHotel(Hotel hotel) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("name", hotel.getName());
        lhm.put("star", String.valueOf(hotel.getStar()));
        lhm.put("score", String.valueOf(hotel.getScore()));
        lhm.put("numOfScore",String.valueOf(hotel.getNumOfScore()));
        lhm.put("description", hotel.getDescription());
        DatabaseUtil.updateRowsData("hotel", "id", String.valueOf(hotel.getId()), lhm);
    }

}
