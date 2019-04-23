package com.Nalecy.www.po.forTableView;

import com.Nalecy.www.po.Order;
import com.Nalecy.www.util.ServiceFactory;

import static com.Nalecy.www.constantClass.RoomPeriod.*;


public class OrderT extends Order {
    private Order order;
    private String roomTime;
    private String hotelName;
    private String roomName;
    public OrderT(Order order) {
        super(order.getId(),order.getUserName(),order.getDate(),order.getRoomPeriod(),order.getHotelID(),order.getRoomID(),order.getBalance());
        this.order = order;
        switch (order.getRoomPeriod()){
            case MORNING : roomTime = MORNING_VALUE;break;
            case AFTERNOON : roomTime = AFTERNOON_VALUE;break;
            case NIGHT : roomTime = NIGHT_VALUE;break;
        }
        hotelName = ServiceFactory.getHotelService().getHotel(order.getHotelID()).getName();
        roomName = ServiceFactory.getRoomService().getRoomById(order.getRoomID()).getName();
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
