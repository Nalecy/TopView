package com.Nalecy.www.dao.mysqlDaoImpl;

import com.Nalecy.www.dao.HotelAdminDao;
import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.po.Person;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedHashMap;
import java.util.List;

public class HotelAdminDaoImpl implements HotelAdminDao {
    private static HotelAdminDaoImpl instance = null;
    public static HotelAdminDaoImpl getInstance(){
        if(instance == null)instance = new HotelAdminDaoImpl();
        return instance;
    }
    private HotelAdminDaoImpl(){}

    public void addHotelAdmin(Person person) {
        HotelAdmin hotelAdmin = (HotelAdmin) person;
        DatabaseUtil.addOneRowData("hotelAdmin", String.valueOf(hotelAdmin.getId()), hotelAdmin.getUserName(), hotelAdmin.getPassword(), hotelAdmin.getIdNumber(), hotelAdmin.getTelephone(), String.valueOf(hotelAdmin.getHotelID()));
    }

    public HotelAdmin getHotelAdmin(String userName) {
        HotelAdmin hotelAdmin = new HotelAdmin ();
        List<String> list = DatabaseUtil.getOneRowData("hotelAdmin", "userName", userName);
        if (list == null|| list.size() == 0) return null;
        setInfo(hotelAdmin, list);
        return hotelAdmin;
    }

    public HotelAdmin getHotelAdmin(Integer id) {
        HotelAdmin hotelAdmin = new HotelAdmin();
        List<String> list = DatabaseUtil.getOneRowData("hotelAdmin", "id", String.valueOf(id));
        if (list == null||list.size() == 0) return null;
        setInfo(hotelAdmin, list);
        return hotelAdmin;
    }

    private void setInfo(HotelAdmin hotelAdmin, List<String> list) {
        hotelAdmin.setId(Integer.valueOf(list.get(0)));
        hotelAdmin.setUserName(list.get(1));
        hotelAdmin.setPassword(list.get(2));
        hotelAdmin.setIdNumber(list.get(3));
        hotelAdmin.setTelephone(list.get(4));
        hotelAdmin.setHotelID(Integer.valueOf(list.get(5)));
    }

    public void updateHotelAdmin(HotelAdmin hotelAdmin) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("userName", hotelAdmin.getUserName());
        lhm.put("password", hotelAdmin.getPassword());
        lhm.put("idNumber", hotelAdmin.getIdNumber());
        lhm.put("telephone", hotelAdmin.getTelephone());
        lhm.put("hotelId", String.valueOf(hotelAdmin.getHotelID()));
        DatabaseUtil.updateRowsData("hotelAdmin", "id", String.valueOf(hotelAdmin.getId()), lhm);
    }
}
