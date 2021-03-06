package com.Nalecy.www.view;

import com.Nalecy.www.po.Customer;
import com.Nalecy.www.service.BalanceService;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.DiscountService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.*;
import com.Nalecy.www.view.customerSubView.HotelListView;
import com.Nalecy.www.view.customerSubView.OrderListView;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class CustomerView extends View{
    private BalanceService balanceService = ServiceFactory.getBalanceService();
    private PersonService personService = ServiceFactory.getPersonService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();

    private Stage window;
    private Scene scene;
    private VBox tipLabelVBox;
    private VBox vBox;

    private LabelsCreater tipLabel;
    private Button hotelListButton;
    private Button orderListButton;
    private Button psnlInfoButton;
    private Button rechargeButton;
    private Button toBeVipButton;
    private Button cancelLoginButton;
    private Button backButton;

    @Override
    public void display(){
        if(!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        else {
            refreshData();
        }
        window.show();
    }

    private void refreshData() {
        tipLabel.setLine(2,"您的余额："+  ((Customer) personService.searchPerson( currentRecorder.getCurrentUserName())).getBalance() +"元");
    }

    @Override
    public void close() {
        window.close();
    }

    @Override
    public void hide() {
        window.hide();
    }

    private void setButtonAction(){
        hotelListButton.setOnAction( e -> {
            ViewManger.switchView(new HotelListView());
        });
        orderListButton.setOnAction( e -> {
            ViewManger.switchView( new OrderListView());
        });
        psnlInfoButton.setOnAction( e -> {
            try{
                modifyInfo();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
        });
        rechargeButton.setOnAction(e->{
            try{
                recharge();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
            refreshData();
        });
        toBeVipButton.setOnAction(e->{
            try{
                toBeVip();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
        });
        cancelLoginButton.setOnAction(e -> {
            try{
                personService.cancelLogin(currentRecorder.getCurrentUserName());
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
            PromptAlert.display("恭喜","取消成功");
        });
        backButton.setOnAction( e -> {
            ViewManger.back();
        });
    }

    private void toBeVip() {
        //获取当前用户名
        String userName = currentRecorder.getCurrentUserName();
        //判断是否是VIP
        if(DiscountService.getInstance().isVip(userName))
            //是
            PromptAlert.display("提示","你本来就是VIP了");
        else {
            //不是 成为VIP
            if(DiscountService.getInstance().toBeVip(userName)){
                PromptAlert.display("恭喜","您成为了VIP");
            }else {
                PromptAlert.display("错误","成为VIP失败");
            }
        }
    }

    private void recharge() {
        //获取编辑框构造器对象
        InfoEditPopup rechargePopup = new InfoEditPopup();
        //设置编辑框的标签
        rechargePopup.setInfoNameList("你想充值(元): ");
        //启动窗口并准备获取其返回值
        List<String> rb = rechargePopup.display("充值");
        //如果返回值为空,直接返回
        if(rb == null)return;
        //判断是否是数字
        if(!RegexUtil.isNumber(rb.get(0))){
            //不是 向用户报错
            PromptAlert.display("错误","请输入正确的充值金额");
            return;
        }
        int balance = Integer.parseInt(rb.get(0));
        //充值
        balanceService.recharge(balance);
        PromptAlert.display("成功","充值成功");
    }

    private void modifyInfo() {
        //获取编辑框构造器对象
        InfoEditPopup editPopup = new InfoEditPopup();
        //先获取当前登录用户的用户名再获取对应顾客对象
        Customer customer = (Customer) personService.searchPerson(currentRecorder.getCurrentUserName());
        //设置编辑框的标签
        editPopup.setInfoNameList("密码","身份证号码","电话");
        //设置编辑框的值
        editPopup.setInfoValueList(customer.getPassword(),customer.getIdNumber(),customer.getTelephone());
        //启动窗口并准备获取其返回值
        List<String> infoList = editPopup.display("个人信息修改");  //启动窗口并准备获取其返回值
        if(infoList != null) {
            //正则判断
            if(!RegexUtil.isPassword(infoList.get(0))){ PromptAlert.display("错误","检查密码输入");return;}
            if(!RegexUtil.isIdCard(infoList.get(1))){PromptAlert.display("错误","检查身份证输入");return;}
            if(!RegexUtil.isTelephone(infoList.get(2))){PromptAlert.display("错误","检查手机号输入");return;}
            //将返回的值列表分别赋值
            customer.setPassword(infoList.get(0));
            customer.setIdNumber(infoList.get(1));
            customer.setTelephone(infoList.get(2));
            personService.updatePeron(customer);       //保存信息
        }
    }
    /** 初始化布局元素 */
    private void init(){
        String userName = currentRecorder.getCurrentUserName();
        Customer customer = (Customer) personService.searchPerson(userName);

        tipLabel = new LabelsCreater();
        tipLabel.addLine("今天是"+ DateUtil.getCurrentDate());
        tipLabel.addLine("您好,用户名为"+ userName +"的顾客。");
        tipLabel.addLine("您的余额："+ customer.getBalance() +"元");
        tipLabelVBox = tipLabel.getVBox();
        tipLabelVBox.setAlignment(Pos.CENTER);

        hotelListButton = ComponentCreater.newButton("查看酒店",300);
        orderListButton = ComponentCreater.newButton("查看订单",300);
        psnlInfoButton = ComponentCreater.newButton("修改个人信息",300);
        rechargeButton = ComponentCreater.newButton("充值",300);
        toBeVipButton = ComponentCreater.newButton("成为VIP",300);
        cancelLoginButton = ComponentCreater.newButton("取消自动登录",300);
        backButton = ComponentCreater.newButton("退出",300);

        vBox = new VBox();
        vBox.getChildren().addAll(tipLabelVBox,hotelListButton,orderListButton,psnlInfoButton,rechargeButton,toBeVipButton,cancelLoginButton,backButton);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50));
        vBox.setAlignment(Pos.CENTER);

        scene = new Scene(vBox);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("顾客菜单");
        window.setResizable(false);
        window.setMinWidth(700);
        window.setMinHeight(400);

    }
}
