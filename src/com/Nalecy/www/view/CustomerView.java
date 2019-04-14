package com.Nalecy.www.view;

import com.Nalecy.www.dao.UserDao;
import com.Nalecy.www.po.Customer;
import com.Nalecy.www.po.User;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.customerSubView.HotelListView;
import com.Nalecy.www.view.customerSubView.OrderListView;
import com.Nalecy.www.view.customerSubView.PsnlInfoView;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Arrays;
import java.util.List;

public class CustomerView extends View{
    /*private static CustomerView instance;
    private CustomerView(){}
    public static CustomerView getInstance(){
        if(instance == null){
            instance = new CustomerView();
        }
        return instance;
    }*/

    private Stage window;
    private Scene scene;
    private VBox vBox;

    private Button hotelListButton;
    private Button orderListButton;
    private Button psnlInfoButton;
    private Button backButton;

    @Override
    public void display(){
        init();
        setButtonAction();
        window.show();
    }

    @Override
    public void close() {
        window.close();
    }

    private void setButtonAction(){
        hotelListButton.setOnAction( e -> {
            ViewManger.switchView(this, new HotelListView());
        });
        orderListButton.setOnAction( e -> {
            ViewManger.switchView(this, new OrderListView());
        });
        psnlInfoButton.setOnAction( e -> {
            modifyInfo();
        });
        backButton.setOnAction( e -> {
            ViewManger.switchView(this, new LoginView());
        });

    }

    private void modifyInfo() {
        InfoEditPopup editPopup = new InfoEditPopup();
        Customer customer = (Customer) PersonService.getInstance().searchPerson(HotelService.getInstance().getCurrentUser());//先获取当前登录用户的用户名再获取对应顾客对象
        editPopup.setInfoNameList("密码","身份证号码","电话");
        editPopup.setInfoValueList(customer.getPassword(),customer.getIdNumber(),customer.getTelephone());
        List<String> infoList = editPopup.display("个人信息修改");  //启动窗口并准备获取其返回值
        if(infoList != null) {
            //将返回的值列表分别赋值
            customer.setPassword(infoList.get(0));
            customer.setIdNumber(infoList.get(1));
            customer.setTelephone(infoList.get(2));
            PersonService.getInstance().updatePeron(customer);       //保存信息
        }
    }

    private void init(){
        hotelListButton = new Button("查看酒店");
        orderListButton = new Button("查看订单");
        psnlInfoButton = new Button("修改个人信息");
        backButton = new Button("退出");

        hotelListButton.setMinWidth(300);
        orderListButton.setMinWidth(300);
        psnlInfoButton.setMinWidth(300);
        backButton.setMinWidth(300);

        vBox = new VBox();
        vBox.getChildren().addAll(hotelListButton,orderListButton,psnlInfoButton,backButton);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50,200,50,200));

        scene = new Scene(vBox);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("顾客菜单");
        window.setResizable(false);
        window.setMinWidth(700);
        window.setMinHeight(400);

    }
}
