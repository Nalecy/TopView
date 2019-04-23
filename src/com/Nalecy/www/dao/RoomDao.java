package com.Nalecy.www.dao;


import com.Nalecy.www.po.Room;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class RoomDao {
    public static void addRoom(Room room) {
        DatabaseUtil.addOneRowData("room", null, room.getName(), String.valueOf(room.getType()), String.valueOf(room.getArea()), String.valueOf(room.getBedWidth()), String.valueOf(room.getHotelID()), String.valueOf(room.getPrice()));
    }

    public static Room getRoom(String roomName) {
        Room room = new Room();
        List<String> roomInfo = DatabaseUtil.getOneRowData("room", "name", roomName);
        if (roomInfo == null) return null;
        setInfo(room, roomInfo);
        return room;
    }

    public static Room getRoom(Integer roomId) {
        Room room = new Room();
        List<String> roomInfo = DatabaseUtil.getOneRowData("room", "id", String.valueOf(roomId));
        if (roomInfo == null) return null;
        setInfo(room, roomInfo);
        return room;
    }

    public static List<Room> getRoomList() {
        List<String> idList = DatabaseUtil.getOneColumnData("room", "id");
        List<Room> rooms = new ArrayList<>();
        if (idList == null) return null;
        for (String id : idList) {
            rooms.add(getRoom(Integer.valueOf(id)));
        }
        return rooms;
    }

    private static void setInfo(Room room, List<String> roomInfo) {
        room.setId(Integer.valueOf(roomInfo.get(0)));
        room.setName(roomInfo.get(1));
        room.setType(Integer.valueOf(roomInfo.get(2)));
        room.setArea(Integer.valueOf(roomInfo.get(3)));
        room.setBedWidth(Integer.valueOf(roomInfo.get(4)));
        room.setHotelID(Integer.valueOf(roomInfo.get(5)));
        room.setPrice(Integer.valueOf(roomInfo.get(6)));
    }

    public static void deleteRoom(Integer roomId) {
        DatabaseUtil.deleteOneRowData("room", "id", String.valueOf(roomId));
    }

    public static void updateRoom(Room room) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("name", room.getName());
        lhm.put("type", String.valueOf(room.getType()));
        lhm.put("area", String.valueOf(room.getArea()));
        lhm.put("bedWidth", String.valueOf(room.getBedWidth()));
        lhm.put("price", String.valueOf(room.getPrice()));
        lhm.put("hotelID", String.valueOf(room.getHotelID()));

        DatabaseUtil.updateRowsData("room", "id", String.valueOf(room.getId()), lhm);
    }


}
