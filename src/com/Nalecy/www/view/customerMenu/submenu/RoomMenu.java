package com.Nalecy.www.view.customerMenu.submenu;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.view.Menu;

import java.sql.Date;
import java.util.Calendar;
import java.util.regex.Pattern;

public class RoomMenu extends Menu {
    @Override
    public void show() {
        DateService ds = DateService.getInstance();
        System.out.println("下列为该房间的信息：");
        System.out.println(RoomService.getInstance().getCurrentRoom());
        System.out.println("你想要预定的时间是：");
        System.out.println("1、" + ds.getMonth() + "月" + ds.getDay() + "日");
        ds.addDay(1);
        System.out.println("2、" + ds.getMonth() + "月" + ds.getDay() + "日");
        ds.addDay(1);
        System.out.println("3、" + ds.getMonth() + "月" + ds.getDay() + "日");
        ds.initCalendar();
        String dateChoice = in.next();
        if(dateChoice.equals("0"))return;
        if (Pattern.matches("^[123]{1}$", dateChoice)) {
            System.out.println("你想预约的时间段是(输入0返回)：");
            System.out.println("1、上午");
            System.out.println("2、下午");
            System.out.println("3、晚上");
            String timeChoice = in.next();
            if(timeChoice.equals("0"))return;
            if (Pattern.matches("^[123]{1}$", timeChoice)) {
                reserve(dateChoice,timeChoice);
            } else System.out.println("请检查输入");
        } else System.out.println("请检查输入");


    }

    private void reserve(String dateChoice, String timeChoice) {

        /*if(RoomService.getInstance().reserve(dateChoice)){
            System.out.println("预定成功");
        }else {
            System.out.println("不好意思，该时间段已被预约了");
        }*/
    }
}
