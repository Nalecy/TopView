package com.Nalecy.www.dao.daoFactory;

import com.Nalecy.www.dao.*;
import com.Nalecy.www.dao.mysqlDaoImpl.*;

public class DaoFactory {
    private DaoFactory() {
        throw new AssertionError("请勿实例化DaoFactory");
    }

    public static AccountDao getAccountDao() {
        return AccountDaoImpl.getInstance();
    }

    public static AdministratorDao getAdministratorDao() {
        return AdministratorDaoImpl.getInstance();
    }

    public static CommentDaoImpl getCommentDao() {
        return CommentDaoImpl.getInstance();
    }

    public static CustomerDao getCustomerDao() {
        return CustomerDaoImpl.getInstance();
    }

    public static HotelAdminDao getHotelAdminDao() {
        return HotelAdminDaoImpl.getInstance();
    }

    public static HotelDao getHotelDao() {
        return HotelDaoImpl.getInstance();
    }

    public static UserDao getUserDao() {
        return UserDaoImpl.getInstance();
    }

    public static RoomDao getRoomDao() {
        return RoomDaoImpl.getInstance();
    }

    public static OrderDao getOrderDao() {
        return OrderDaoImpl.getInstance();
    }
}
