package com.Nalecy.www.dao;

import com.Nalecy.www.po.Room;

import java.util.List;

public interface RoomDao {
    void addRoom(Room room);
    Room getRoom(String roomName);
    Room getRoom(Integer roomId);
    List<Room> getRoomList();
    void deleteRoom(Integer roomId);
    void updateRoom(Room room);
}
