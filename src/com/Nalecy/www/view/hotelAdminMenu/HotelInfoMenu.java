package com.Nalecy.www.view.hotelAdminMenu;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.view.Menu;

public class HotelInfoMenu extends Menu {//废弃
    @Override
    public void show() {
        HotelAdmin ha = (HotelAdmin) PersonService.getInstance().searchPerson(HotelService.getInstance().getCurrentUser());
        Hotel hotel = HotelService.getInstance().getHotel(ha.getHotelID());
        System.out.println("下列是您任职的酒店信息：");
        System.out.println(hotel.toString());
        System.out.println("输入任意值返回......");
        in.nextLine();
    }
}
