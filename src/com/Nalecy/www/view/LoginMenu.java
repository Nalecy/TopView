package com.Nalecy.www.view;


import com.Nalecy.www.Runner;
import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.service.ProGetter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Pattern;

public class LoginMenu extends Menu{

    private String userName = null;
    private String password = null;
    private String telephone = null;
    private String idNumber = null;


    @Override
    public void show() {
        while(true) {
            printMenu();
            String choice = in.next();
            if (Pattern.matches("^[0-2]$",choice)) {
                int c = Integer.parseInt(choice);
                if(c == 0)break;
                switch (c){
                    case 1:
                        registerUser();break;
                    case 2:
                        login();break;
                }
            } else System.out.println("请检查输入");
        }
    }

    private void login() {
        System.out.println("请输入用户名：");
        userName = in.next();
        String password = PersonService.getPassword(userName);
        if(password == null){
            System.out.println("用户名不存在");
            return;
        }
        Boolean b = null;
        if (PersonService.hasLogin(userName)){
            b = true;
        }else {
            System.out.println("请输入密码：");
            this.password = in.next();
            b = this.password.equals(password);
            if (b) {
                //保存密码
                System.out.println("登陆成功，是否保存密码：");
                System.out.println("保存 --任意键 \t\t 取消 --0 ");
                if(!in.next().equals("0")) {
                    if (PersonService.saveUser(userName)) System.out.println("保存成功");
                    else System.out.println("保存失败");
                }
            }
            else {
                System.out.println("密码错误");
                return;
            }
        }
        HotelService.getInstance().setCurrentUser(userName);
        Integer p = PersonService.searchPerson(PersonService.getPersonID(userName)).getPermission();
        switch (p){
            case 1:showNextMenu(new CustomerMenu());
                break;
            case 2:showNextMenu(new HotelAdminMenu());
                break;
            case 3:showNextMenu(new AdministratorMenu());
                break;
        }
    }



    private void showNextMenu(Menu menu) {
        menu.show();
    }

    private void registerUser() {
        while(true) {
            System.out.println("你想要注册为：");
            System.out.println("1、顾客");
            System.out.println("2、酒店管理员");
            System.out.println("3、超级管理员");
            System.out.println("0、返回");
            String choice = in.next();
            if (Pattern.matches("^[0-3]$",choice)) {
                int c = Integer.parseInt(choice);
                if(c == 0)break;
                switch (c){
                    case 1:
                        registerCum();break;
                    case 2:
                        System.out.println("请输入注册码：");
                        if(checkPermission("HotelAdmin", in.next())){
                            System.out.println("注册码错误");
                            break;
                        }
                        registerHAdmin();break;
                    case 3:
                        System.out.println("请输入注册码：");
                        if(checkPermission("Administrator", in.next())){
                        System.out.println("注册码错误");
                        break;
                    }
                        registerAdmin();break;
                }
            } else System.out.println("请检查输入");
        }

    }

    private boolean checkPermission(String s, String input) {

            return !ProGetter.getInstance().get(s).equals(input);

    }

    private boolean inputInfo(){    //也许以后会增加正则判断输入规范，故先定义为boolean
        System.out.println("请输入用户名：");
        userName = in.next();
        System.out.println("请输入密码：");
        password = in.next();
        System.out.println("请输入联系电话：");
        telephone = in.next();
        System.out.println("请输入身份证号：");
        idNumber = in.next();
        return true;
    }

    private void registerCum() {
        if(!inputInfo()){
            return;
        }
        Customer c = new Customer();
        c.setUserName(userName);
        c.setPassword(password);
        c.setTelephone(telephone);
        c.setIdNumber(idNumber);
        c.setBalance(0);
        //这里插入 保存至数据库语句
    }

    private void registerHAdmin() {
        if(!inputInfo()){
            return;
        }
        HotelAdmin ha = new HotelAdmin();
        ha.setUserName(userName);
        ha.setPassword(password);
        ha.setTelephone(telephone);
        ha.setIdNumber(idNumber);
        ha.setHotelID(null);
        //这里插入 保存至数据库语句
    }

    private void registerAdmin() {
        if(!inputInfo()){
            return;
        }
        Administrator a = new Administrator();
        a.setUserName(userName);
        a.setPassword(password);
        a.setTelephone(telephone);
        a.setIdNumber(idNumber);
        //这里插入 保存至数据库语句
    }

    private void printMenu(){
        System.out.println("欢迎进入酒店管理系统");
        System.out.println("您想要：");
        System.out.println("1、注册新用户");
        System.out.println("2、登录老用户");
        System.out.println("0、退出");
    }
}
