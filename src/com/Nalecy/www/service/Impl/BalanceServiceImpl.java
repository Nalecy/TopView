package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.AccountDao;
import com.Nalecy.www.po.Account;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.*;
import com.Nalecy.www.util.ServiceFactory;

import java.util.ArrayList;
import java.util.List;
public class BalanceServiceImpl implements BalanceService {
    private static BalanceServiceImpl instance;
    public static BalanceServiceImpl getInstance(){
        if(instance == null)
            instance = new BalanceServiceImpl();
        return instance;
    }
    //定义需要引用的其他服务
    private PersonService personService;
    private RoomService roomService;
    private CurrentRecorder currentRecorder;
    private boolean hasInit = false;

    private void initService() {
        //调用前初始化服务且确保只初始化一次
        if (!hasInit) {
            personService = ServiceFactory.getPersonService();
            roomService = ServiceFactory.getRoomService();
            currentRecorder = ServiceFactory.getCurrentRecorder();
            hasInit = true;
        }
    }

    @Override
    public boolean payment(Order order) {
        initService();
        //获取价格
        Integer price = order.getBalance() ;
        //获取顾客
        Customer customer = (Customer) personService.searchPerson(order.getUserName());
        //确保余额充足
        if (customer.getBalance() < price) return false;
        else {
            //添加账单
            addAccount(order);
            //扣款
            customer.setBalance(customer.getBalance() - price);
            personService.updatePeron(customer);
            return true;
        }
    }

    @Override
    public void recharge(Integer number) {
        initService();
        //获取当前已登录的用户
        Customer customer = (Customer) personService.searchPerson(currentRecorder.getCurrentUserName());
        //根据传入的金额充值 并更新数据库信息
        customer.setBalance(customer.getBalance() + number);
        personService.updatePeron(customer);
    }

    @Override
    public void refund(Order order) {
        initService();
        //获取用户
        Customer customer = (Customer) personService.searchPerson(order.getUserName());
        //获取账单
        addAccount(order);
        //通过账单金额退款
        customer.setBalance(customer.getBalance() + order.getBalance());
        personService.updatePeron(customer);
    }

    @Override
    public List<Account> getAccountList() {
        initService();
        List<Account> allList = AccountDao.getAccountList();
        List<Account> accountList = new ArrayList<>();
        if (allList != null) {
            for (Account account : allList)
                if (account.getHotelId().equals(currentRecorder.getCurrentHotelId()))
                    accountList.add(account);
        }

        return accountList;
    }
    private void addAccount(Order order) {
        Account account = new Account();
        //组装账单
        account.setHotelId(order.getHotelID());
        account.setCustomerId(personService.searchPerson(order.getUserName()).getId());
        account.setDate(order.getDate());
        account.setBalance(order.getBalance());
        account.setRoomId(order.getRoomID());
        account.setRoomPeriod(order.getRoomPeriod());
        //添加账单
        AccountDao.addAccount(account);
    }
}
