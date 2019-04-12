package com.Nalecy.www.view;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.view.customerMenu.HotelListMenu;
import com.Nalecy.www.view.customerMenu.OrderMenu;
import com.Nalecy.www.view.customerMenu.PsnlInfoMenu;

import java.util.regex.Pattern;

public class CustomerMenu extends Menu {//废弃
    @Override
    public void show() {
        while (true) {
            printMenu();
            String choice = in.next();
            if(choice.equals("0"))break;
            if(Pattern.matches("^[1289]$",choice)){
                Integer c = new Integer(choice);
                switch (c){
                    case 1:showNextMenu(new HotelListMenu());break;
                    case 2:showNextMenu(new OrderMenu());break;
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
        System.out.println("欢迎,用户名为"+ HotelService.getInstance().getCurrentUser()+"的顾客。");
        System.out.println("您想要：");
        System.out.println("1、查看酒店");
        System.out.println("2、查看订单");
        System.out.println("8、取消自动登录");
        System.out.println("9、修改个人信息");
        System.out.println("0、退出至登录界面");
    }

    private void cancelLogin() {
        if (PersonService.getInstance().cancelLogin(HotelService.getInstance().getCurrentUser())) System.out.println("取消成功");
        else System.out.println("删除失败");
    }
}
