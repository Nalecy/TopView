package com.Nalecy.www.service;

import com.Nalecy.www.dao.OrderDao;
import com.Nalecy.www.dao.RoomDao;
import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.Room;

import java.sql.Date;
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

    public boolean reserve(Date date, Integer timeChoice) {
        List<Order> allOrders = OrderService.getInstance().getIncompleteOrder();
        for (Order order : allOrders) {
            Date orderDay = order.getDate();
            if (order.getRoomID().equals(currentRoom.getId()))          //查询是否有人预定过该房间
                if(orderDay.getTime()+8*3600*1000==date.getTime())
                    if(order.getRoomPeriod().equals(timeChoice))
                        return false;
        }
        Order order = new Order();
        order.setUserName(HotelService.getInstance().getCurrentUser());
        order.setRoomID(currentRoom.getId());
        order.setRoomPeriod(timeChoice);
        order.setDate(date);
        order.setHotelID(currentRoom.getHotelID());
        OrderDao.addOrder(order);
        return true;
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
