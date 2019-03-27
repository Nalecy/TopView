package com.Nalecy.www.service;


//import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateService {
    private static Date currentDate;

    private static DateService ourInstance;

    public static DateService getInstance() {
        if(ourInstance == null)ourInstance = new DateService();
        return ourInstance;
    }

    private DateService() {
        //获取数据库时间
        currentDate = new Date(0);
    }
    public String getCurrentDate(){
        return currentDate.toString();
        // return new SimpleDateFormat("yyyy年MM月dd日").format(currentDate);
    }
    public void incrDate(){
        currentDate.setTime(currentDate.getTime() + 3600*1000*24);
    }
}
