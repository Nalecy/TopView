package com.Nalecy.www.discardMenu.customerMenu;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.discardMenu.Menu;
import com.Nalecy.www.discardMenu.customerMenu.submenu.HotelMenu;

import java.util.List;

public class HotelListMenu  extends Menu {//废弃

    @Override
    public void show() {
        List<Hotel> hotelList;
        Hotel hotel;
        hotelList = HotelService.getInstance().getHotelList();
        while (true) {
            hotel = null;
            System.out.println("下列是酒店列表 (输入0返回)");
            for (Hotel h : hotelList) {
                System.out.println(h.toString());
            }
            System.out.println("您想进入那个酒店(请输入名字)：");
            String name = in.nextLine();
            if (name.equals("0"))break;                 //输入0退出
            for (Hotel h : hotelList) {
                if(h.getName().equals(name)){
                    hotel = h;
                    break;
                }
            }
            if(hotel == null){
                System.out.println("无该酒店！");
            }else {
                HotelService.getInstance().setCurrentHotel(hotel);
                showNextMenu(new HotelMenu());
            }
        }

    }
}
