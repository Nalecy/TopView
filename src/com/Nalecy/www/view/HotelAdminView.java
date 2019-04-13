package com.Nalecy.www.view;

import com.Nalecy.www.po.HotelAdmin;
import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelAdminView extends View{
    /*private static HotelAdminView instance;
    private HotelAdminView(){}
    public static HotelAdminView getInstance(){
        if(instance == null){
            instance = new HotelAdminView();
        }
        return instance;
    }*/
    private HotelAdmin user;
    public HotelAdminView(){
        user = (HotelAdmin) PersonService.getInstance().searchPerson(HotelService.getInstance().getCurrentUser());
    }


    private Stage stage;
    private Scene scene;
    private VBox vBox;

    private Label dateLabel;
    private Label tipLabel1;
    private Label tipLabel2;
    private Label tipLabel3;

    private Button infoButton;
    private Button roomButton;
    private Button orderButton;
    private Button cancelLoginButton;
    private Button psnlInfoButton;
    private Button backButton;

    public void display(){
        HotelService.getInstance().setCurrentHotel(HotelService.getInstance().getHotel(user.getHotelID()));
        init();
        setButtonAction();
        stage.show();
    }

    @Override
    public void close() {
        stage.close();
    }

    private void setButtonAction() {
        infoButton.setOnAction(e -> {
            PromptAlert.display("酒店信息",HotelService.getInstance().getCurrentHotel().toString());
        });
        roomButton.setOnAction(e -> {

        });
        orderButton.setOnAction(e -> {

        });
        cancelLoginButton.setOnAction(e -> {

        });
        psnlInfoButton.setOnAction(e -> {

        });
        backButton.setOnAction(e -> {
            ViewManger.switchView(this, new LoginView());

        });
    }

    private void init(){
        dateLabel = new Label("今天是"+ DateService.getInstance().getCurrentDate());
        tipLabel1 = new Label("您好,用户名为"+ HotelService.getInstance().getCurrentUser()+"的酒店管理员。");
        tipLabel2 = new Label("您是"+HotelService.getInstance().getHotel(user.getHotelID()).getName()+"酒店的管理员");
        tipLabel3 = new Label("您可以：");

        infoButton = new Button("查看本酒店信息");
        infoButton.setMinWidth(150);
        roomButton = new Button("管理本酒店房间");
        roomButton.setMinWidth(150);
        orderButton = new Button("管理本酒店订单");
        orderButton.setMinWidth(150);
        cancelLoginButton = new Button("取消自动登录");
        cancelLoginButton.setMinWidth(150);
        psnlInfoButton = new Button("修改个人信息");
        psnlInfoButton.setMinWidth(150);
        backButton = new Button("退出至登录界面");
        backButton.setMinWidth(150);

        vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);
        vBox.getChildren().addAll(dateLabel,tipLabel1,tipLabel2,tipLabel3,infoButton,roomButton,orderButton,cancelLoginButton,psnlInfoButton,backButton);

        scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("酒管菜单");
    }
}