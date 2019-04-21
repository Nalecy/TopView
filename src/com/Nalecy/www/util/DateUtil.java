package com.Nalecy.www.util;



import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public class DateUtil {
    private static Date currentDate;
    private static Calendar calendar ;
    static {
        currentDate = Date.valueOf(DatabaseUtil.getOneColumnData("system_date","nowDate").get(0));     //获取时间
        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
    }

    private DateUtil() {
    }
    public static Integer getYear(){
        return calendar.get(Calendar.YEAR);
    }
    public static Integer getMonth(){
        return calendar.get(Calendar.MONTH) + 1;
    }
    public static Integer getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getCurrentDate(){
        return currentDate;
    }

    /**
     * 时间流逝一天
     */
    public static void incrDate(){
        Date originalDate = new Date(currentDate.getTime());
        currentDate.setTime(currentDate.getTime() + 3600*1000*24);
        Map<String,String> map = new LinkedHashMap<>();
        map.put("nowDate",currentDate.toString());
        DatabaseUtil.updateRowsData("system_date","nowDate",originalDate.toString(),map);
    }

    /**
     * 传入一个整数 n ，获取 n 天后的Date对象
     * @param numAfterToday
     * @return
     */
    public static Date getOneDay(Integer numAfterToday) {
        long time = numAfterToday*3600*1000* 24;
        Date date = new Date(currentDate.getTime()+ time);
        return date;
    }

}
