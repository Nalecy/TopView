package com.Nalecy.www.service;

public class HotelService {
    private String currentUser = null;
    private static HotelService ourInstance = new HotelService();
    public static HotelService getInstance() {
        return ourInstance;
    }

    private HotelService() {
    }

    public void setCurrentUser(String userName){
        currentUser = userName;
    }
    public String getCurrentUser() {
        return currentUser;
    }
}
