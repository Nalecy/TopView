package com.Nalecy.www.dao.mysqlDaoImpl;

import com.Nalecy.www.constantClass.IsComment;
import com.Nalecy.www.dao.OrderDao;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.util.DatabaseUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static OrderDaoImpl instance = null;
    public static OrderDaoImpl getInstance(){
        if(instance == null)instance = new OrderDaoImpl();
        return instance;
    }
    private OrderDaoImpl(){}

    public void addOrder(Order order) {
        DatabaseUtil.addOneRowData("orderR", null, order.getUserName(), order.getDate().toString(), String.valueOf(order.getRoomPeriod()), String.valueOf(order.getHotelID()), String.valueOf(order.getRoomID()), String.valueOf(order.getBalance()), String.valueOf(order.getIsComment().ordinal()));
    }

    public Order getOrder(String userName) {
        Order order = new Order();
        List<String> orderInfo = DatabaseUtil.getOneRowData("orderR", "userName", userName);
        if (orderInfo == null) return null;
        setInfo(order, orderInfo);
        return order;
    }

    public Order getOrder(Integer orderId) {
        Order order = new Order();
        List<String> orderInfo = DatabaseUtil.getOneRowData("orderR", "id", String.valueOf(orderId));
        if (orderInfo == null) return null;
        setInfo(order, orderInfo);
        return order;
    }

    public List<Order> getOrderList() {
        List<String> idList = DatabaseUtil.getOneColumnData("orderR", "id");
        List<Order> orders = new ArrayList<>();
        if (idList == null) return null;
        for (String id : idList) {
            orders.add(getOrder(Integer.valueOf(id)));
        }
        return orders;
    }

    private void setInfo(Order order, List<String> orderInfo) {
        order.setId(Integer.valueOf(orderInfo.get(0)));
        order.setUserName(orderInfo.get(1));
        order.setDate(Date.valueOf(orderInfo.get(2)));
        order.setRoomPeriod(Integer.valueOf(orderInfo.get(3)));
        order.setHotelID(Integer.valueOf(orderInfo.get(4)));
        order.setRoomID(Integer.valueOf(orderInfo.get(5)));
        order.setBalance(Integer.valueOf(orderInfo.get(6)));
        order.setIsComment(IsComment.get(Integer.parseInt(orderInfo.get(7))));
    }

    public void deleteOrder(Integer id) {
        DatabaseUtil.deleteOneRowData("orderR", "id", String.valueOf(id));
    }

    public void updateOrder(Order order) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("id", String.valueOf(order.getId()));
        lhm.put("userName", String.valueOf(order.getUserName()));
        lhm.put("date", String.valueOf(order.getDate()));
        lhm.put("roomPeriod", String.valueOf(order.getRoomPeriod()));
        lhm.put("hotelID", String.valueOf(order.getHotelID()));
        lhm.put("roomID", String.valueOf(order.getRoomID()));
        lhm.put("isComment", String.valueOf(order.getIsComment().ordinal()));

        DatabaseUtil.updateRowsData("orderR", "id", String.valueOf(order.getId()), lhm);
    }


}
