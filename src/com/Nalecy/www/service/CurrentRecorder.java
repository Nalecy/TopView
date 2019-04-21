package com.Nalecy.www.service;


public interface CurrentRecorder {

    /**
     * 传入当前已登录的用户名并保存下来已备调用
     * @param userName
     */
    void setCurrentUserName(String userName);

    /**
     * 返回已存入的的用户名
     * @return String
     */
    String getCurrentUserName();

    /**
     * 传入当前已登录的酒店ID并保存下来已备调用
     * @param currentHotelId
     */
    void setCurrentHotelId(Integer currentHotelId);

    /**
     * 返回已存入的的酒店ID
     * @return Integer
     */
    Integer getCurrentHotelId();

    /**
     * 传入当前已选择的房间ID并保存下来已备调用
     * @param currentRoomId
     */
    void setCurrentRoomId(Integer currentRoomId);

    /**
     * 返回已存入的的房间ID
     * @return Integer
     */
    Integer getCurrentRoomId();
}
