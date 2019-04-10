package com.Nalecy.www.view.hotelAdminMenu;

import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.view.Menu;
import com.Nalecy.www.view.hotelAdminMenu.submenu.RoomMenu;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RoomListMenu extends Menu {
    @Override
    public void show() {
        /*LinkedHashMap<Integer, Room> roomList = HotelService.getInstance().getRoomList();
        while (true) {
            System.out.println("该酒店有下列房间,您想要:(输入0返回)");
            for (Integer key : roomList.keySet()) {
                System.out.println(key + "、" + roomList.get(key).toString());
            }
            System.out.println("1、增加房间   2、删除房间   3、修改房间");
            String choice = in.next();
            if(choice.equals("0"))break;
            if(choice.equals("1"))addRoom();
            else if(choice.equals("2"))deleteRoom(roomList);
            else if(choice.equals("3")) modifyRoom(roomList);
            else System.out.println("请检查输入");
        }*/

    }

    private void addRoom() {
        Room room = new Room();
        String value;
        System.out.println("请输入房间类型(1、普通  2、高级)：");
        value = in.next();
        if (Pattern.matches("^[12]$", value))room.setType(Integer.parseInt(value));
        else { System.out.println("请检查输入");return; }
        System.out.println("请输入房间面积 /平方：");
        value = in.next();
        if (Pattern.matches("^\\d+$", value))room.setArea(Integer.parseInt(value));
        else { System.out.println("请检查输入");return; }
        System.out.println("请输入床宽 /厘米：");
        value = in.next();
        if (Pattern.matches("^\\d+$", value))room.setBedWidth(Integer.parseInt(value));
        else { System.out.println("请检查输入");return; }
        System.out.println("请输入价格 /元：");
        value = in.next();
        if (Pattern.matches("^\\d+$", value))room.setPrice(Integer.parseInt(value));
        else { System.out.println("请检查输入");return; }
        room.setHotelID(HotelService.getInstance().getCurrentHotel().getId());
        System.out.println("请确认信息");
        System.out.println(room);
        System.out.println("输入1保存，任意值取消");
        if(in.next().equals("1"))
            if(RoomService.getInstance().addRoom(room)) System.out.println("增加成功");
            else System.out.println("增加失败");
        else System.out.println("请检查输入");
    }

    private void deleteRoom(LinkedHashMap<Integer, Room> roomList) {
        System.out.println("请输入你想删除的序号");
        String choice = in.next();
        if(choice.equals("0"))return;
        if (Pattern.matches("^\\d+$", choice) && roomList.keySet().contains(Integer.parseInt(choice))) {
            System.out.println("确定删除以下房间？：(1,确定  任意值,返回)");
            System.out.println(roomList.get(Integer.parseInt(choice)));
            if(in.next().equals("1")) {
                RoomService.getInstance().deleteRoom(roomList.get(Integer.parseInt(choice)).getId());
                System.out.println("删除成功");
            }
            else System.out.println("返回");

        } else System.out.println("请检查输入");
    }

    private void modifyRoom(LinkedHashMap<Integer, Room> roomList){
        System.out.println("你想要修改的房间序号是：");
        String choice = in.next();
        if(choice.equals("0"))return;
        if (Pattern.matches("^\\d+$", choice) && roomList.keySet().contains(Integer.parseInt(choice))) {
            RoomService.getInstance().setCurrentRoom(roomList.get(Integer.parseInt(choice)));
            showNextMenu(new RoomMenu());
        } else System.out.println("请检查输入");
    }
}
