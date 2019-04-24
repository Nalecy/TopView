package com.Nalecy.www.service.Impl;

import com.Nalecy.www.po.Order;
import com.Nalecy.www.service.BalanceService;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.ServiceFactory;

public class OrderWithBalServiceImpl extends OrderServiceImpl implements OrderService {
    private static OrderWithBalServiceImpl instance;
    public static OrderWithBalServiceImpl getInstance(){
        if(instance == null)
            instance = new OrderWithBalServiceImpl();
        return instance;
    }

    private BalanceService balanceService;
    private boolean hasInit = false;

    private void initService() {
        if (!hasInit) {
            balanceService = ServiceFactory.getBalanceService();
            hasInit = true;
        }
    }



    @Override
    public void cancelOrder(Order order) {
        initService();
        balanceService.refund(order);
        super.cancelOrder(order);

    }

    @Override
    public boolean addOrder(Order order) {
        initService();
        if (balanceService.payment(order)){
            super.addOrder(order);
            return true;
        } else{
          return false;
        }
    }

}
