package com.Nalecy.www.util;



import java.sql.Date;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

public final class DateUtil {
    private static Date currentDate;
    private static Calendar calendar ;
    static {
        currentDate = Date.valueOf(DatabaseUtil.getOneColumnData("system_date","nowDate").get(0));     //获取时间
        calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
    }

    private DateUtil() {
        throw new AssertionError("请勿实例化DateUtil");
    }

    /**
     * 获取当前的年份
     * @return Integer
     */
    public static Integer getYear(){
        return calendar.get(Calendar.YEAR);
    }
    /**
     * 获取当前的月份
     * @return Integer
     */
    public static Integer getMonth(){
        return calendar.get(Calendar.MONTH) + 1;
    }
    /**
     * 获取当前的日
     * @return Integer
     */
    public static Integer getDay(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前的日期
     * @return sql.Date
     */
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
     * @param numAfterToday n
     * @return sql.Date
     */
    public static Date getOneDay(Integer numAfterToday) {
        long time = numAfterToday*3600*1000* 24;
        Date date = new Date(currentDate.getTime()+ time);
        return date;
    }

}
