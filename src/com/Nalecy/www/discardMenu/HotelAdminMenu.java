package com.Nalecy.www.discardMenu;

import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.discardMenu.hotelAdminMenu.HotelInfoMenu;
import com.Nalecy.www.discardMenu.hotelAdminMenu.OrderMenu;
import com.Nalecy.www.discardMenu.hotelAdminMenu.PsnlInfoMenu;
import com.Nalecy.www.discardMenu.hotelAdminMenu.RoomListMenu;

import java.util.regex.Pattern;

public class HotelAdminMenu extends Menu {//废弃
    private HotelAdmin user = (HotelAdmin) PersonService.getInstance().searchPerson(HotelService.getInstance().getCurrentUser());
    @Override
    public void show() {
        HotelService.getInstance().setCurrentHotel(HotelService.getInstance().getHotel(user.getHotelID()));
        while (true) {
            printMenu();
            String choice = in.next();
            if(choice.equals("0"))break;
            if(Pattern.matches("^[12389]$",choice)){
                Integer c = new Integer(choice);
                switch (c){
                    case 1:showNextMenu(new HotelInfoMenu());break;
                    case 2:showNextMenu(new RoomListMenu());break;
                    case 3:showNextMenu(new OrderMenu());break;
                    case 8:cancelLogin();break;
                    case 9:showNextMenu(new PsnlInfoMenu());break;
                }
            }else {
                System.out.println("请检查输入");
            }
        }
    }

    private void printMenu() {
        System.out.println("\t\t\t今天是"+ DateService.getInstance().getCurrentDate());
        System.out.println("您好,用户名为"+ user.getUserName()+"的酒店管理员。");
        System.out.println("您是"+HotelService.getInstance().getHotel(user.getHotelID()).getName()+"酒店的管理员");
        System.out.println("您想要：");
        System.out.println("1、查看本酒店信息");
        System.out.println("2、管理本酒店房间");
        System.out.println("3、管理本酒店订单");
        System.out.println("8、取消自动登录");
        System.out.println("9、修改个人信息");
        System.out.println("0、退出至登录界面");
    }
    private void cancelLogin() {
        if (PersonService.getInstance().cancelLogin(HotelService.getInstance().getCurrentUser())) System.out.println("取消成功");
        else System.out.println("删除失败");
    }
}
