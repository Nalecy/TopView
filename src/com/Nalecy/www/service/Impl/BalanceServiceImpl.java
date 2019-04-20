package com.Nalecy.www.service.Impl;

import com.Nalecy.www.dao.AccountDao;
import com.Nalecy.www.po.Account;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.BalanceService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.ServiceFactory;

public class BalanceServiceImpl implements BalanceService {

    private HotelService hotelService;
    private PersonService personService;
    private RoomService roomService;
    private boolean hasInit = false;

    private void initService() {
        if (!hasInit) {
            hotelService = ServiceFactory.getHotelService();
            personService = ServiceFactory.getPersonService();
            roomService = ServiceFactory.getRoomService();
            hasInit = true;
        }
    }

    @Override
    public boolean payment(Order order) {
        initService();
        //获取价格
        Integer price = roomService.getRoomById(order.getRoomID()).getPrice();
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
        Customer customer = (Customer) personService.searchPerson(hotelService.getCurrentUser());
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

    private void addAccount(Order order) {
        Account account = new Account();
        account.setHotelId(order.getHotelID());
        account.setCustomerId(personService.searchPerson(order.getUserName()).getId());
        account.setDate(order.getDate());
        account.setBalance(order.getBalance());
        account.setRoomId(order.getRoomID());
        account.setRoomPeriod(order.getRoomPeriod());
        AccountDao.addAccount(account);
    }
}
