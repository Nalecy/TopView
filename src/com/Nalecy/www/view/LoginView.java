package com.Nalecy.www.view;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.RootGetter;
import com.Nalecy.www.view.alert.ConfirmAlert;
import com.Nalecy.www.view.alert.PromptAlert;
import javafx.application.Application;
import javafx.fxml.FXML;
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
    private static LoginView instance;
    public static LoginView getInstance(){
        if(instance == null){
            instance = new LoginView();
        }
        return instance;
    }
    //布局要素
    private Stage window;
    private Scene scene;
    @FXML
    private GridPane grid;

    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button exitButton;

    @FXML
    private Label dateLabel;
    @FXML
    private Label userLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField userText;
    @FXML
    private PasswordField passwordText;

    public void display() {
        init();
       // setButtonAction();
        window.show();
    }

    private void setButtonAction() {
        loginButton.setOnAction(e -> {
            login();
        });
        registerButton.setOnAction(e ->{
            RegisterView.getInstance().display();
            window.close();
        });
        exitButton.setOnAction(e -> window.close());
    }


    private void init(){
        dateLabel.setText("当前时间："+ DateService.getInstance().getCurrentDate());

        scene = new Scene(RootGetter.getFxmlRoot("LoginView.fxml"));
        window = new Stage();
        window.setScene(scene);
        window.setResizable(false);
        window.setTitle("登录页面");

    }

    private void login() {
        String userName = userText.getText();
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
        HotelService.getInstance().setCurrentUser(userText.getText());
        Integer p = PersonService.getInstance().searchPerson(PersonService.getInstance().getPersonID(userName)).getPermission();
        switch (p) {
            case 1:
                CustomerView.getInstance().display();
                window.close();
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
