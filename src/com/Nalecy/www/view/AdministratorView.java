package com.Nalecy.www.view;

import com.Nalecy.www.po.Administrator;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.*;
import com.Nalecy.www.view.administratorSubView.HotelManagerView;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AdministratorView extends View{
    private PersonService personService = ServiceFactory.getPersonService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();

    private Stage stage;
    private Scene scene;
    private VBox vBox;

    private LabelsCreater labelsCreater;
    private VBox labelVBox ;

    private Button hotelManagerButton;
    private Button addDateButton;
    private Button cancelLoginButton;
    private Button psnlInfoButton;
    private Button backButton;
    @Override
    public void display() {
        if(!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        stage.show();
    }

    private void setButtonAction() {
        hotelManagerButton.setOnAction(e->{
            ViewManger.switchView(new HotelManagerView());
        });
        addDateButton.setOnAction(e->{
            DateUtil.incrDate();
            PromptAlert.display("成功","成功流逝");
            labelsCreater.setLine(0,"今天是"+ DateUtil.getCurrentDate());
        });
        cancelLoginButton.setOnAction(e->{
            personService.cancelLogin(currentRecorder.getCurrentUserName());
            PromptAlert.display("恭喜","取消成功");
        });
        psnlInfoButton.setOnAction(e->{
            modifyInfo();
        });
        backButton.setOnAction(e->{
            ViewManger.switchView(new LoginView());
        });
    }

    private void modifyInfo() {
        InfoEditPopup editPopup = new InfoEditPopup();
        Administrator administrator = (Administrator) personService.searchPerson(currentRecorder.getCurrentUserName());//先获取当前登录用户的用户名再获取对应顾客对象
        editPopup.setInfoNameList("密码","身份证号码","电话");
        editPopup.setInfoValueList(administrator.getPassword(),administrator.getIdNumber(),administrator.getTelephone());
        List<String> infoList = editPopup.display("个人信息修改");  //启动窗口并准备获取其返回值
        if(infoList != null) {
            //正则判断
            if(!RegexUtil.isPassword(infoList.get(0))){PromptAlert.display("错误","检查密码输入");return;}
            if(!RegexUtil.isIdCard(infoList.get(1))){PromptAlert.display("错误","检查身份证输入");return;}
            if(!RegexUtil.isTelephone(infoList.get(2))){PromptAlert.display("错误","检查手机号输入");return;}

            //将返回的值列表分别赋值
            administrator.setPassword(infoList.get(0));
            administrator.setIdNumber(infoList.get(1));
            administrator.setTelephone(infoList.get(2));
            personService.updatePeron(administrator);       //保存信息·
        }
    }
    /** 初始化布局元素 */
    private void init(){
        labelsCreater = new LabelsCreater();
        labelsCreater.addLine("今天是"+ DateUtil.getCurrentDate());
        labelsCreater.addLine("您好,用户名为"+ currentRecorder.getCurrentUserName()+"的超级管理员。");
        labelsCreater.addLine("您可以：");
        labelVBox = labelsCreater.getVBox();

        hotelManagerButton = ComponentCreater.newButton("管理所有酒店");
        addDateButton = ComponentCreater.newButton("时间流逝～（一天）");
        cancelLoginButton = ComponentCreater.newButton("取消自动登录");
        psnlInfoButton = ComponentCreater.newButton("修改个人信息");
        backButton = ComponentCreater.newButton("返回");

        vBox = new VBox();
        vBox.getChildren().addAll(labelVBox,hotelManagerButton,addDateButton,cancelLoginButton,psnlInfoButton,backButton);
        vBox.setPadding(new Insets(20));
        vBox.setSpacing(20);
        scene = new Scene(vBox);
        stage = new Stage();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("超级管理员菜单");

    }

    @Override
    public void close() {
        stage.close();
    }

    @Override
    public void hide() {
        stage.hide();
    }
}
