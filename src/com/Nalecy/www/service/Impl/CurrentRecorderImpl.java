package com.Nalecy.www.service.Impl;

import com.Nalecy.www.service.CurrentRecorder;

public class CurrentRecorderImpl implements CurrentRecorder {
    private static CurrentRecorder instance;
    public static CurrentRecorder getInstance(){
        if(instance == null)
        instance = new CurrentRecorderImpl();
        return instance;
    }

    private String currentUserName = "";
    private Integer currentHotelId = 0;
    private Integer currentRoomId = 0;
    private Integer currentOrderId = 0;
    @Override
    public void setCurrentUserName(String userName) {
        currentUserName = userName;
    }

    @Override
    public String getCurrentUserName() {
        return currentUserName;
    }

    @Override
    public Integer getCurrentHotelId() {
        return currentHotelId;
    }

    @Override
    public void setCurrentHotelId(Integer hotelId) {
        currentHotelId = hotelId;
    }

    @Override
    public Integer getCurrentRoomId() {
        return currentRoomId;
    }

    @Override
    public void setCurrentRoomId(Integer roomId) {
        currentRoomId = roomId;
    }

    @Override
    public void setCurrentOrderId(Integer orderId) { currentOrderId = orderId; }

    @Override
    public Integer getCurrentOrderId() { return currentOrderId; }
}
