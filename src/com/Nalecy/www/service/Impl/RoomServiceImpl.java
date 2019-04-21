package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.RoomDao;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.NoMoneyException;
import com.Nalecy.www.util.ServiceFactory;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceImpl implements RoomService {
    private OrderService orderService;
    private CurrentRecorder currentRecorder;
    private boolean hasInit = false;

    private void initService() {
        if (!hasInit) {
            orderService = ServiceFactory.getOrderService();
            currentRecorder = ServiceFactory.getCurrentRecorder();
            hasInit = true;
        }
    }


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
    public boolean reserveRoom(Date date, Integer timeChoice) {
        //初始化服务
        initService();
        //获取当前房间
        Room currentRoom = getRoomById(currentRecorder.getCurrentRoomId());
        //通过检查未完成订单检查是否已预订
        List<Order> allOrders = orderService.getIncompleteOrder();
        if(allOrders != null) {
            for (Order order : allOrders) {
                Date orderDay = order.getDate();
                if (order.getRoomID().equals(currentRoom.getId()))          //查询是否有人预定过该房间
                    if (orderDay.getTime() == date.getTime()) if (order.getRoomPeriod().equals(timeChoice)) return false;
            }
        }
        //组装订单
        Order order = new Order();
        order.setUserName(currentRecorder.getCurrentUserName());
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
        room.setHotelID(currentRecorder.getCurrentHotelId());
        RoomDao.addRoom(room);
        return true;
    }
    @Override
    public void deleteRoom(Integer id) {
        RoomDao.deleteRoom(id);
    }
}
