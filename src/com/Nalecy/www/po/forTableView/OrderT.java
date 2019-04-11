package com.Nalecy.www.po.forTableView;

import com.Nalecy.www.constantClass.RoomPeriod;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;

import static com.Nalecy.www.constantClass.RoomPeriod.*;


public class OrderT extends Order {
    private Order order;
    private String roomTime;
    private String hotelName;
    private String roomName;

    public OrderT(Order order) {
        super(order.getId(),order.getUserName(),order.getDate(),order.getRoomPeriod(),order.getHotelID(),order.getRoomID());
        this.order = order;
        switch (order.getRoomPeriod()){
            case MORNING : roomTime = "早上";break;
            case AFTERNOON : roomTime = "下午";break;
            case NIGHT : roomTime = "晚上";break;
        }
        hotelName = HotelService.getInstance().getHotel(order.getHotelID()).getName();
        roomName = RoomService.getInstance().getRoomById(order.getRoomID()).getName();
    }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public String getRoomTime() { return roomTime; }
    public void setRoomTime(String roomTime) { this.roomTime = roomTime; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
}
