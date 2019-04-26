package com.Nalecy.www.dao;

import com.Nalecy.www.po.Account;

import java.util.List;

public interface AccountDao {
    /**
     * 增加账单
     * @param account 账单对象
     */
    void addAccount(Account account);

    /**
     * 获取账单列表
     * @return List
     */
    List<Account> getAccountList();

    /**
     * 删除账单
     * @param id 账单id
     */
    void deleteAccount(Integer id);

    /**
     * 更新账单
     * @param account 账单对象
     */
    void updateAccount(Account account);

}
