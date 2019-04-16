package com.Nalecy.www.dao;

import com.Nalecy.www.po.Account;
import com.Nalecy.www.util.DatabaseUtil;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class AccountDao {
    public static void addAccount(Account account) {
        DatabaseUtil.addOneRowData("account", null, String.valueOf(account.getOrderId()), account.getDate().toString(), String.valueOf(account.getBalance()));
    }



    public static Account getAccount(Integer id) {
        Account account = new Account();
        LinkedList<String> accountInfo = DatabaseUtil.getOneRowData("account", "id", String.valueOf(id));
        if (accountInfo == null) return null;
        setInfo(account, accountInfo);
        return account;
    }
    public static Account getAccountByOrder(Integer orderId) {
        Account account = new Account();
        LinkedList<String> accountInfo = DatabaseUtil.getOneRowData("account", "orderId", String.valueOf(orderId));
        if (accountInfo == null) return null;
        setInfo(account, accountInfo);
        return account;
    }

    public static List<Account> getAccountList() {
        LinkedList<String> idList = DatabaseUtil.getOneColumnData("account", "orderId");
        List<Account> accounts = new ArrayList<>();
        if (idList == null) return null;
        for (String id : idList) {
            accounts.add(getAccount(Integer.valueOf(id)));
        }
        return accounts;
    }

    private static void setInfo(Account account, List<String> accountInfo) {
        account.setId(Integer.valueOf(accountInfo.get(0)));
        account.setOrderId(Integer.valueOf(accountInfo.get(1)));
        account.setDate(Date.valueOf(accountInfo.get(2)));
        account.setBalance(Integer.valueOf(accountInfo.get(3)));
    }

    public static void deleteAccount(Integer id) {
        DatabaseUtil.deleteOneRowData("account", "id", String.valueOf(id));
    }

    public static void updateAccount(Account account) {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("id", String.valueOf(account.getId()));
        lhm.put("orderId", String.valueOf(account.getOrderId()));
        lhm.put("date", String.valueOf(account.getDate()));
        lhm.put("balance", String.valueOf(account.getBalance()));

        DatabaseUtil.updateRowsData("account", "id", String.valueOf(account.getId()), lhm);
    }
}
