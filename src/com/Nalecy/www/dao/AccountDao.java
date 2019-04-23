package com.Nalecy.www.dao;

import com.Nalecy.www.po.Account;
import com.Nalecy.www.util.DatabaseUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class AccountDao {
    public static void addAccount(Account account) {
        DatabaseUtil.addOneRowData("account", null, String.valueOf(account.getHotelId()), String.valueOf(account.getCustomerId()), String.valueOf(account.getDate()), String.valueOf(account.getBalance()),String.valueOf(account.getRoomId()),String.valueOf(account.getRoomPeriod()));
    }



    public static Account getAccount(Integer id) {
        Account account = new Account();
        List<String> accountInfo = DatabaseUtil.getOneRowData("account", "id", String.valueOf(id));
        if (accountInfo == null) return null;
        setInfo(account, accountInfo);
        return account;
    }
    public static List<Account> getAccountList() {
        List<String> idList = DatabaseUtil.getOneColumnData("account", "id");
        List<Account> accounts = new ArrayList<>();
        if (idList == null) return null;
        for (String id : idList) {
            accounts.add(getAccount(Integer.valueOf(id)));
        }
        return accounts;
    }

    private static void setInfo(Account account, List<String> accountInfo) {
        account.setId(Integer.valueOf(accountInfo.get(0)));
        account.setHotelId(Integer.valueOf(accountInfo.get(1)));
        account.setCustomerId(Integer.valueOf(accountInfo.get(2)));
        account.setDate(Date.valueOf(accountInfo.get(3)));
        account.setBalance(Integer.valueOf(accountInfo.get(4)));
        account.setRoomId(Integer.valueOf(accountInfo.get(5)));
        account.setRoomPeriod(Integer.valueOf(accountInfo.get(6)));
    }

    public static void deleteAccount(Integer id) {
        DatabaseUtil.deleteOneRowData("account", "id", String.valueOf(id));
    }

    public static void updateAccount(Account account) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("id", String.valueOf(account.getId()));
        lhm.put("hotelId", String.valueOf(account.getHotelId()));
        lhm.put("customerId", String.valueOf(account.getCustomerId()));
        lhm.put("date", String.valueOf(account.getDate()));
        lhm.put("balance",String.valueOf(account.getBalance()));
        lhm.put("roomId",String.valueOf(account.getRoomId()));
        lhm.put("roomPeriod",String.valueOf(account.getRoomPeriod()));

        DatabaseUtil.updateRowsData("account", "id", String.valueOf(account.getId()), lhm);
    }
}
