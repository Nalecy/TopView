package com.Nalecy.www.service;

import com.Nalecy.www.po.Room;

import java.sql.Date;
import java.util.List;

public interface RoomService {
    /**
     * 通过酒店ID获取对应酒店的房间列表
     * @param hotelId
     * @return List<Room>
     */
    List<Room> getRoomList(Integer hotelId);

    /**
     * 根据传入的日期和时间选择预定房间，成功预订返回true,若已预订则返回false
     * @param date
     * @param timeChoice
     * @return true/false
     */
    boolean reserveRoom(Date date, Integer timeChoice);

    /**
     * 通过房间ID获取房间实例
     * @param roomID
     * @return Room
     */
    Room getRoomById(Integer roomID);

    /**
     * 更新房间信息
     * @param room
     * @return true/false
     */
    boolean saveRoomInfo(Room room);

    /**
     * 增加一个房间，返回是否成功
     * @param room
     * @return true/false
     */
    boolean addRoom(Room room);

    /**
     * 删除一个房间
     * @param id
     */
    void deleteRoom(Integer id);

}
