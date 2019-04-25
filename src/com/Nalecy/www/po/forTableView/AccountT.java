package com.Nalecy.www.po.forTableView;

import com.Nalecy.www.po.Account;
import com.Nalecy.www.util.ServiceFactory;

import static com.Nalecy.www.constantClass.RoomPeriod.*;

public class AccountT extends Account {
    private String customerUserName;
    private String roomName;
    private String roomPeriodString;

    private Account account;
    public AccountT(Account account){
        super(account.getId(),account.getHotelId(),account.getCustomerId(),account.getDate(),account.getBalance(),account.getRoomId(),account.getRoomPeriod());
        //获取适合展示的信息
        this.account = account;
        customerUserName = ServiceFactory.getPersonService().searchPerson(getCustomerId()).getUserName();
        roomName = ServiceFactory.getRoomService().getRoomById(getRoomId()).getName();
        switch (account.getRoomPeriod()){
            case MORNING : roomPeriodString = MORNING_VALUE;break;
            case AFTERNOON : roomPeriodString = AFTERNOON_VALUE;break;
            case NIGHT : roomPeriodString = NIGHT_VALUE;break;
        }
    }

    public String getCustomerUserName() { return customerUserName; }
    public void setCustomerUserName(String customerUserName) { this.customerUserName = customerUserName; }
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    public String getRoomPeriodString() { return roomPeriodString; }
    public void setRoomPeriodString(String roomPeriodString) { this.roomPeriodString = roomPeriodString; }
    public Account getAccount() { return account; }
    public void setAccount(Account account) { this.account = account; }
}
