package com.Nalecy.www.view;

import com.Nalecy.www.view.customerSubView.HotelListView;
import com.Nalecy.www.view.customerSubView.OrderListView;
import com.Nalecy.www.view.customerSubView.PsnlInfoView;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

public class CustomerView {
    private static CustomerView instance;
    private CustomerView(){}
    public static CustomerView getInstance(){
        if(instance == null){
            instance = new CustomerView();
        }
        return instance;
    }

    private Stage window;
    private Scene scene;
    private VBox vBox;

    private Button hotelListButton;
    private Button orderListButton;
    private Button psnlInfoButton;
    private Button backButton;

    public void display(){
        init();
        setButtonAction();
        window.show();
    }
    private void setButtonAction(){
        hotelListButton.setOnAction( e -> {
            HotelListView.getInstance().display();
            window.close();
        });
        orderListButton.setOnAction( e -> {
            OrderListView.getInstance().display();
            window.close();
        });
        psnlInfoButton.setOnAction( e -> {
            PsnlInfoView.getInstance().display();
            window.close();
        });
        backButton.setOnAction( e -> {
            LoginView.getInstance().display();
            window.close();
        });

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
