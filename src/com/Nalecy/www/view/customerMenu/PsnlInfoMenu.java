package com.Nalecy.www.view.customerMenu;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.view.Menu;

import java.util.regex.Pattern;

public class PsnlInfoMenu extends Menu {//废弃
    private Customer customer;

    public PsnlInfoMenu(){
        customer = (Customer) PersonService.getInstance().searchPerson(HotelService.getInstance().getCurrentUser());
    }
    @Override
    public void show() {

        System.out.println("您好，名为"+customer.getUserName()+"的用户，下面是您的信息：");
        printNowInfo();
        System.out.println("您想要修改(输入1保存，0返回)：");
        while(true){
            String c = in.next();
            if(c.equals("0"))break;
            else if(c.equals("1")){
                if(PersonService.getInstance().savePersonInfo(customer)){
                    System.out.println("保存成功");break;
                }else {
                    System.out.println("保存失败");break;
                }
            }
            if(Pattern.matches("^[2-5]{1}",c)){
                System.out.println("你要将其改为");
                String value = in.next();
                switch (Integer.parseInt(c)){
                    case 2:
                        customer.setPassword(value);
                        break;
                    case 3:
                        if(Pattern.matches("^\\d+",value)) customer.setIdNumber(value);
                        else System.out.println("格式错误");
                        break;
                    case 4:
                        if(Pattern.matches("^\\d+",value)) customer.setTelephone(value);
                        else System.out.println("格式错误");
                        break;
                    case 5:
                        if(Pattern.matches("^\\d+",value)) customer.setBalance(Integer.parseInt(value));
                        else System.out.println("格式错误");
                        break;
                }
            }else System.out.println("请检查输入");
            printNowInfo();

        }
    }

    private void printNowInfo(){
        System.out.println("2、密码："+customer.getPassword());
        System.out.println("3、身份证号："+customer.getIdNumber());
        System.out.println("4、电话："+ customer.getTelephone());
        System.out.println("5、余额："+ customer.getBalance()+"元");
    }
}

