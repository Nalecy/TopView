package com.Nalecy.www.view.customerMenu.submenu;

import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.view.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class HotelMenu extends Menu {
    @Override
    public void show() {
        /*LinkedHashMap<Integer, Room> roomList = HotelService.getInstance().getRoomList();
        while (true) {
            System.out.println("该酒店有下列房间,您想查看:(输入0返回)");
            for (Integer key : roomList.keySet()) {
                System.out.println(key + "、" + roomList.get(key).toString());
            }
            String choice = in.next();
            if(choice.equals("0"))break;
            if (Pattern.matches("^\\d+$", choice) && roomList.keySet().contains(Integer.parseInt(choice))) {
                RoomService.getInstance().setCurrentRoom(roomList.get(Integer.parseInt(choice)));
                showNextMenu(new RoomMenu());
            } else System.out.println("请检查输入");
        }*/
    }
}
