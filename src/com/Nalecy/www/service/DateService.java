package com.Nalecy.www.service;


//import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Calendar;

public class DateService {
    private static Date currentDate;
    private static Calendar calendar ;
    private static DateService ourInstance;

    public static DateService getInstance() {
        if(ourInstance == null)ourInstance = new DateService();
        return ourInstance;
    }

    private DateService() {
        //获取数据库时间
        currentDate = new Date(3600*24*1000);
        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
    }
    public Integer getYear(){
        return calendar.get(Calendar.YEAR);
    }
    public Integer getMonth(){
        return calendar.get(Calendar.MONTH) + 1;
    }
    public Integer getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public Date getCurrentDate(){
        return currentDate;
        // return new SimpleDateFormat("yyyy年MM月dd日").format(currentDate);
    }
    public void incrDate(){
        currentDate.setTime(currentDate.getTime() + 3600*1000*24);
        //保存至数据库
    }

    public void initCalendar(){
        calendar.setTime(currentDate);
    }

    public Date getOneDay(Integer numAfterToday) {
        long time = numAfterToday*3600*1000* 24;
        Date date = new Date(currentDate.getTime()+ time);
        return date;
    }


}
