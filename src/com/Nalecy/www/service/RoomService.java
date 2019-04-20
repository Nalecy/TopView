package com.Nalecy.www.service;

import com.Nalecy.www.po.Room;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    List<Room> getRoomList(Integer hotelId);
    Room getCurrentRoom();
    void setCurrentRoom(Room currentRoom);
    boolean reserveRoom(Date date, Integer timeChoice);
    Room getRoomById(Integer roomID);
    boolean saveRoomInfo(Room room);
    boolean addRoom(Room room);
    void deleteRoom(Integer id);
}
