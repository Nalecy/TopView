package com.Nalecy.www.util;

import com.Nalecy.www.service.*;
import com.Nalecy.www.service.Impl.*;

public class ServiceFactory {

    private static HotelService hotelService = new HotelServiceImpl();
    public static HotelService getHotelService() {
        return hotelService;
    }

    private static PersonService personService = new PersonServiceImpl();
    public static PersonService getPersonService() {
        return personService;
    }

    private static RoomService roomService = new RoomServiceImpl();
    public static RoomService getRoomService() {
        return roomService;
    }

    private static OrderService orderService = new OrderWithBalServiceImpl();
    public static OrderService getOrderService() {
        return orderService;
    }

    private static BalanceService balanceService = new BalanceServiceImpl();
    public static BalanceService getBalanceService() {
        return balanceService;
    }

    private static CurrentRecorder currentRecorder = CurrentRecorderImpl.getInstance();
    public static CurrentRecorder getCurrentRecorder() {
        return currentRecorder;
    }
}
