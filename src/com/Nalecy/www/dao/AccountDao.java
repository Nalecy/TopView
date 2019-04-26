package com.Nalecy.www.dao;

import com.Nalecy.www.po.Account;

import java.util.List;

public interface AccountDao {
    void addAccount(Account account);

    List<Account> getAccountList();

    void deleteAccount(Integer id);

    void updateAccount(Account account);

}
