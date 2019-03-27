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
}
