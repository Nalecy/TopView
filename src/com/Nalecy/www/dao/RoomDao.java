package com.Nalecy.www.dao;

import com.Nalecy.www.po.Room;

import java.util.List;

public interface RoomDao {
    /**
     * 增加房间
     * @param room 房间对象
     */
    void addRoom(Room room);

    /**
     * 通过房间id获取房间对象
     * @param roomId 房间id
     * @return Room 房间对象
     */
    Room getRoom(Integer roomId);

    /**
     * 获取所有房间的列表
     * @return List<Room>
     */
    List<Room> getRoomList();

    /**
     * 根据房间id删除房间
     * @param roomId 房间id
     */
    void deleteRoom(Integer roomId);

    /**
     * 更新房间
     * @param room 房间对象
     */
    void updateRoom(Room room);
}
