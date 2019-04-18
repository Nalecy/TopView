package com.Nalecy.www.util;


//import java.text.SimpleDateFormat;

import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class DateUtil {
    private static Date currentDate;
    private static Calendar calendar ;
    private static DateUtil ourInstance;

    public static DateUtil getInstance() {
        if(ourInstance == null)ourInstance = new DateUtil();
        return ourInstance;
    }

    private DateUtil() {
        //获取数据库时间
        currentDate = Date.valueOf(DatabaseUtil.getOneColumnData("system_date","nowDate").get(0));     //获取时间
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
    }
    public void incrDate(){
        Date originalDate = new Date(currentDate.getTime());
        currentDate.setTime(currentDate.getTime() + 3600*1000*24);
        Map<String,String> map = new LinkedHashMap<>();
        map.put("nowDate",currentDate.toString());
        DatabaseUtil.updateRowsData("system_date","nowDate",originalDate.toString(),map);
    }


    public Date getOneDay(Integer numAfterToday) {
        long time = numAfterToday*3600*1000* 24;
        Date date = new Date(currentDate.getTime()+ time);
        return date;
    }

}
