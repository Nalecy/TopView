package com.Nalecy.www.view;

import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.*;
import com.Nalecy.www.view.hadminSubView.AccountListView;
import com.Nalecy.www.view.hadminSubView.OrderMangerView;
import com.Nalecy.www.view.hadminSubView.RoomMangerView;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class HotelAdminView extends View{
    private HotelService hotelService = ServiceFactory.getHotelService();
    private PersonService personService = ServiceFactory.getPersonService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();

    private HotelAdmin user;
    public HotelAdminView(){
        user = (HotelAdmin) personService.searchPerson(currentRecorder.getCurrentUserName());
    }


    private Stage stage;
    private Scene scene;
    private VBox vBox;

    private LabelsCreater labelsCreater;
    private VBox labelVBox ;

    private Button infoButton;
    private Button roomButton;
    private Button orderButton;
    private Button accountButton;
    private Button cancelLoginButton;
    private Button psnlInfoButton;
    private Button backButton;

    public void display(){
        if(!hasInit) {
            currentRecorder.setCurrentHotelId(user.getHotelID());
            init();
            setButtonAction();
            hasInit = true;
        }
        stage.show();
    }

    @Override
    public void close() {
        stage.close();
    }

    @Override
    public void hide() {
        stage.hide();
    }

    private void setButtonAction() {
        infoButton.setOnAction(e -> {
            PromptAlert.display("酒店信息", hotelService.getHotel(currentRecorder.getCurrentHotelId()).toString());
        });
        roomButton.setOnAction(e -> {
            ViewManger.switchView(new RoomMangerView());
        });
        orderButton.setOnAction(e -> {
            ViewManger.switchView(new OrderMangerView());
        });
        accountButton.setOnAction(e->{
            ViewManger.switchView(new AccountListView());
        });
        cancelLoginButton.setOnAction(e -> {
            personService.cancelLogin(currentRecorder.getCurrentUserName());
        });
        psnlInfoButton.setOnAction(e -> {
            modifyInfo();
        });
        backButton.setOnAction(e -> {
            ViewManger.back();
        });
    }
    private void modifyInfo() {
        InfoEditPopup editPopup = new InfoEditPopup();
        HotelAdmin hotelAdmin = (HotelAdmin) personService.searchPerson(currentRecorder.getCurrentUserName());//先获取当前登录用户的用户名再获取对应顾客对象
        editPopup.setInfoNameList("密码","身份证号码","电话");
        editPopup.setInfoValueList(hotelAdmin.getPassword(),hotelAdmin.getIdNumber(),hotelAdmin.getTelephone());
        List<String> infoList = editPopup.display("个人信息修改");  //启动窗口并准备获取其返回值
        if(infoList != null) {
            //正则判断
            if(!RegexUtil.isPassword(infoList.get(0))){PromptAlert.display("错误","检查密码输入");return;}
            if(!RegexUtil.isIdCard(infoList.get(1))){PromptAlert.display("错误","检查身份证输入");return;}
            if(!RegexUtil.isTelephone(infoList.get(2))){PromptAlert.display("错误","检查手机号输入");return;}
            //将返回的值列表分别赋值
            hotelAdmin.setPassword(infoList.get(0));
            hotelAdmin.setIdNumber(infoList.get(1));
            hotelAdmin.setTelephone(infoList.get(2));
            personService.updatePeron(hotelAdmin);       //保存信息·
        }
    }

    private void init(){
        labelsCreater = new LabelsCreater();
        labelsCreater.addLine("今天是"+ DateUtil.getCurrentDate());
        labelsCreater.addLine("您好,用户名为"+ currentRecorder.getCurrentUserName()+"的酒店管理员。");
        labelsCreater.addLine("您是"+ hotelService.getHotel(user.getHotelID()).getName()+"酒店的管理员");
        labelsCreater.addLine("您可以：");
        labelVBox = labelsCreater.getVBox();

        infoButton = ComponentCreater.newButton("查看本酒店信息",150);
        roomButton = ComponentCreater.newButton("管理本酒店房间",150);
        orderButton = ComponentCreater.newButton("管理本酒店订单",150);
        accountButton = ComponentCreater.newButton("查看本酒店账单",150);
        cancelLoginButton = ComponentCreater.newButton("取消自动登录",150);
        psnlInfoButton = ComponentCreater.newButton("修改个人信息",150);
        backButton = ComponentCreater.newButton("退出至登录界面",150);

        vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);
        vBox.getChildren().addAll(labelVBox,infoButton,roomButton,orderButton,accountButton,cancelLoginButton,psnlInfoButton,backButton);

        scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("酒管菜单");
    }
}