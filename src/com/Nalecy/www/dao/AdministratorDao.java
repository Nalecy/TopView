package com.Nalecy.www.dao;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Person;
import com.Nalecy.www.util.DatabaseUtil;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class AdministratorDao {
    public static void addAdministrator(Person person) {
        Administrator administrator = (Administrator)person;
        DatabaseUtil.addOneRowData("administrator", String.valueOf(administrator.getId()), administrator.getUserName(), administrator.getPassword(), administrator.getIdNumber(), administrator.getTelephone());
    }

    public static Administrator searchAdministrator(String userName) {
        Administrator administrator = new Administrator();
        LinkedList<String> list = DatabaseUtil.getOneRowData("administrator", "userName", userName);
        if (list == null||list.size() == 0) return null;
        setInfo(administrator,list);
        return administrator;
    }

    public static Administrator searchAdministrator(Integer id) {
        Administrator administrator = new Administrator();
        LinkedList<String> list = DatabaseUtil.getOneRowData("administrator", "id", String.valueOf(id));
        if (list == null|| list.size() == 0) return null;
        setInfo(administrator,list);
        return administrator;
    }

    private static void setInfo(Administrator administrator, List<String> list) {
        administrator.setId(Integer.valueOf(list.get(0)));
        administrator.setUserName(list.get(1));
        administrator.setPassword(list.get(2));
        administrator.setIdNumber(list.get(3));
        administrator.setTelephone(list.get(4));
    }

    public static void updateAdministrator(Administrator administrator) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("userName", administrator.getUserName());
        lhm.put("password", administrator.getPassword());
        lhm.put("idNumber", administrator.getIdNumber());
        lhm.put("telephone", administrator.getTelephone());
        DatabaseUtil.updateRowsData("administrator", "id", String.valueOf(administrator.getId()), lhm);
    }
}
