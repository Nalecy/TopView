package com.Nalecy.www.service;

import com.Nalecy.www.dao.RoomDao;
import com.Nalecy.www.po.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomService {
    private static RoomService ourInstance;
    private Room currentRoom = null;
    public static RoomService getInstance() {
        if(ourInstance == null)ourInstance = new RoomService();
        return ourInstance;
    }

    private RoomService() {
    }

    public List< Room> getRoomList(Integer hotelId) { //测试
        List< Room> allRoom = RoomDao.getRoomList();
        if(allRoom == null)return null;

        List<Room> roomList = new ArrayList<>();
        for (Room room : allRoom) {
            if(room.getHotelID().equals(hotelId))
                roomList.add(room);
        }
        return roomList;
    }
    public Room getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    public boolean reserve(Integer dateChoice,Integer timeChoice) {
        return true;        //若预定成功返回true，否则返回false
    }

    public Room getRoomById(Integer roomID) {
        return RoomDao.getRoom(roomID);
    }

    public boolean saveRoomInfo(Room room) {
        return true;
    }

    public boolean addRoom(Room room) {

        return true;
    }

    public void deleteRoom(Integer id) {

    }
}
