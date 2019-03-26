package com.Nalecy.www.view;

import com.Nalecy.www.service.HotelService;

public class CustomerMenu extends Menu {
    @Override
    public void show() {
        System.out.println("欢迎,用户名为"+ HotelService.getInstance().getCurrentUser()+"的顾客。");
    }
}
