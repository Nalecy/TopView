package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.RoomDao;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.NoMoneyException;
import com.Nalecy.www.util.ServiceFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private HotelService hotelService;
    private OrderService orderService;
    private boolean hasInit = false;

    private void initService() {
        if (!hasInit) {
            hotelService = ServiceFactory.getHotelService();
            orderService = ServiceFactory.getOrderService();
            hasInit = true;
        }
    }


    private Room currentRoom = null;

    @Override
    public List< Room> getRoomList(Integer hotelId) {
        initService();
        List< Room> allRoom = RoomDao.getRoomList();
        if(allRoom == null)return null;

        List<Room> roomList = new ArrayList<>();
        for (Room room : allRoom) {
            if(room.getHotelID().equals(hotelId))
                roomList.add(room);
        }
        return roomList;
    }
    @Override
    public Room getCurrentRoom() { return currentRoom; }
    @Override
    public void setCurrentRoom(Room currentRoom) { this.currentRoom = currentRoom; }
    @Override
    public boolean reserveRoom(Date date, Integer timeChoice) {
        initService();
        List<Order> allOrders = orderService.getIncompleteOrder();
        if(allOrders != null) {
            for (Order order : allOrders) {
                Date orderDay = order.getDate();
                if (order.getRoomID().equals(currentRoom.getId()))          //查询是否有人预定过该房间
                    if (orderDay.getTime() == date.getTime()) if (order.getRoomPeriod().equals(timeChoice)) return false;
            }
        }
        Order order = new Order();
        order.setUserName(hotelService.getCurrentUser());
        order.setRoomID(currentRoom.getId());
        order.setRoomPeriod(timeChoice);
        order.setDate(date);
        order.setHotelID(currentRoom.getHotelID());
        order.setBalance(currentRoom.getPrice());
        if(!orderService.addOrder(order))throw new NoMoneyException();
        return true;
    }
    @Override
    public Room getRoomById(Integer roomID) {
        return RoomDao.getRoom(roomID);
    }
    @Override
    public boolean saveRoomInfo(Room room) {
        RoomDao.updateRoom(room);
        return true;
    }
    @Override
    public boolean addRoom(Room room) {
        initService();
        room.setHotelID(hotelService.getCurrentHotel().getId());
        RoomDao.addRoom(room);
        return true;
    }
    @Override
    public void deleteRoom(Integer id) {
        RoomDao.deleteRoom(id);
    }
}
