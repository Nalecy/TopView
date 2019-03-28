package com.Nalecy.www.service;

import com.Nalecy.www.po.Room;

import java.util.ArrayList;

public class RoomService {
    private static RoomService ourInstance;
    private Room currentRoom = null;
    public static RoomService getInstance() {
        if(ourInstance == null)ourInstance = new RoomService();
        return ourInstance;
    }

    private RoomService() {
    }


    public Room getCurrentRoom() { return currentRoom; }
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }

    public boolean reserve(String dateChoice) {
        return true;        //若预定成功返回true，否则返回false
    }

    public Room getRoomById(Integer roomID) {
        return new Room(1,1,1,1,1,1);
    }
}
