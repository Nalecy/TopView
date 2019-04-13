package com.Nalecy.www.discardMenu.customerMenu.submenu;

import com.Nalecy.www.discardMenu.Menu;

public class HotelMenu extends Menu {//废弃
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
