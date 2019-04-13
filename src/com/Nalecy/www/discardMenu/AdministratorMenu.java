package com.Nalecy.www.discardMenu;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;

public class AdministratorMenu extends Menu{
    @Override
    public void show() {
        printMenu();
    }
    private void printMenu() {
        System.out.println("\t\t\t今天是"+ DateService.getInstance().getCurrentDate());
        System.out.println("您好,用户名为"+ HotelService.getInstance().getCurrentUser()+"的超级管理员。");
        System.out.println("您想要：");
        System.out.println("1、查看酒店");
        System.out.println("2、查看日记");
        System.out.println("8、取消自动登录");
        System.out.println("9、修改个人信息");
        System.out.println("0、退出至登录界面");
    }
}
