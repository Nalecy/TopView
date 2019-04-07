package com.Nalecy.www.view;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.view.alert.ConfirmAlert;
import com.Nalecy.www.view.alert.PromptAlert;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginView{
    //布局要素
    private static Stage window;
    private static Scene scene;
    private static GridPane grid;

    private static Button loginButton;
    private static Button registerButton;
    private static Button exitButton;

    private static Label dateLabel;
    private static Label userLabel;
    private static Label passwordLabel;
    private static TextField userText;
    private static PasswordField passwordText;
    //业务逻辑要素
    private static String userName = null;
    private static String password = null;
    private static String telephone = null;
    private static String idNumber = null;

    public static void display() {
        init();
        loginButton.setOnAction(e -> {
            login();
        });
        registerButton.setOnAction(e ->{
            System.out.println("正在注册");
            PromptAlert.display("aa","Aa");
        });
        window.show();
    }


    private static void init(){
        loginButton = new Button("登录");
        registerButton = new Button("注册");
        exitButton = new Button("退出");

        dateLabel = new Label("当前时间："+ DateService.getInstance().getCurrentDate());
        userLabel = new Label("用户名：");
        passwordLabel = new Label("密码：");

        userText = new TextField();
        userText.setPromptText("请输入用户名");
        passwordText = new PasswordField();

        GridPane.setConstraints(dateLabel,1,0);
        GridPane.setConstraints(userLabel,0,1);
        GridPane.setConstraints(userText,1,1);
        GridPane.setConstraints(passwordLabel,0,2);
        GridPane.setConstraints(passwordText,1,2);
        GridPane.setConstraints(loginButton,0,3);
        GridPane.setConstraints(registerButton,1,3);
        GridPane.setConstraints(exitButton,2,3);

        grid = new GridPane();
        grid.setPadding(new Insets(20,20,20,20));
        grid.setVgap(10);
        grid.setHgap(10);
        grid.getChildren().addAll(dateLabel,loginButton,registerButton,exitButton,userLabel,passwordLabel,userText,passwordText);

        scene = new Scene(grid);

        window = new Stage();
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("登录页面");

    }

    private static void login() {
        userName = userText.getText();
        String password = PersonService.getInstance().getPassword(userName);
        if (password == null) {
            PromptAlert.display("错误","用户名不存在");
            return;
        }
        Boolean b = null;
        if (PersonService.getInstance().hasLogin(userName)) {
            b = true;
        } else {
            password = passwordText.getText();
            b = password.equals(PersonService.getInstance().getPassword(userName));
            if (b) {
                if (ConfirmAlert.display("保存","确定保存密码？")) {
                    if (PersonService.getInstance().saveLogin(userName)) PromptAlert.display("恭喜","保存成功");
                    else PromptAlert.display("错误","保存失败");
                }
            } else {
                PromptAlert.display("错误","密码错误");
                return;
            }
        }
        HotelService.getInstance().setCurrentUser(userName);
        Integer p = PersonService.getInstance().searchPerson(PersonService.getInstance().getPersonID(userName)).getPermission();
        switch (p) {
            case 1:
                PromptAlert.display("顾客菜单","进入顾客菜单");
                break;
            case 2:
                window.close();
                PromptAlert.display("酒管菜单","进入酒管菜单");
                break;
            case 3:
                PromptAlert.display("超管菜单","进入超管菜单");
                break;
        }

    }

}
