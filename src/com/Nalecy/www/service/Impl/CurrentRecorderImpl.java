package com.Nalecy.www.service.Impl;

import com.Nalecy.www.service.CurrentRecorder;

public class CurrentRecorderImpl implements CurrentRecorder {
    private static CurrentRecorder instance = new CurrentRecorderImpl();
    public static CurrentRecorder getInstance(){
        return instance;
    }

    private String currentUserName = "";
    private Integer currentHotelId = 0;
    private Integer currentRoomId = 0;
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
}
