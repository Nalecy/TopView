package com.Nalecy.www.util;

import com.Nalecy.www.service.*;
import com.Nalecy.www.service.Impl.*;

public final class ServiceFactory {
    private ServiceFactory(){
        throw  new AssertionError("请勿实例化ServiceFactory");
    }


    /**
     * @return 酒店服务类的实例
     */
    public static HotelService getHotelService() {
        return HotelServiceImpl.getInstance();
    }

    /**
     * @return 用户服务类的实例
     */
    public static PersonService getPersonService() {
        return PersonServiceImpl.getInstance();
    }

    /**
     * @return 房间服务类的实例
     */
    public static RoomService getRoomService() {
        return RoomServiceImpl.getInstance();
    }

    /**
     * @return 订单服务类的实例
     */
    public static OrderService getOrderService() {
        return OrderWithBalServiceImpl.getInstance();
    }

    /**
     * @return 资金服务类的实例
     */
    public static BalanceService getBalanceService() {
        return BalanceServiceImpl.getInstance();
    }

    /**
     * @return 状态记录类的实例
     */
    public static CurrentRecorder getCurrentRecorder() {
        return CurrentRecorderImpl.getInstance();
    }
}
