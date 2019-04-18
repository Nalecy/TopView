package com.Nalecy.www.view;

import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.popupUtil.ConfirmAlert;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginView extends View{
    /*private static LoginView instance;
    private LoginView(){}
    public static LoginView getInstance(){
        if(instance == null){
            instance = new LoginView();
        }
        return instance;
    }*/
    //布局要素
    private Stage window;
    private Scene scene;
    private GridPane grid;

    private Button loginButton;
    private Button registerButton;
    private Button exitButton;

    private Label dateLabel;
    private Label userLabel;
    private Label passwordLabel;
    private TextField userText;
    private PasswordField passwordText;

    @Override
    public void display() {
        if(!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        window.show();
    }
    @Override
    public void close(){
        window.close();
    }

    @Override
    public void hide() {
        window.hide();
    }

    private void setButtonAction() {

        loginButton.setOnAction(e -> {
            login();
        });

        exitButton.setOnAction(e -> window.close());


        registerButton.setOnAction(e ->{
            ViewManger.switchView(new RegisterView());
        });
        userText.textProperty().addListener((observable, oldValue, newValue) -> {
            if(PersonService.getInstance().hasLogin(userText.getText())){
                String password = PersonService.getInstance().getPassword(userText.getText());
                passwordText.setText(password);
            }
        });
    }


    private void init(){
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
                ViewManger.switchView(new CustomerView());

                break;
            case 2:
                ViewManger.switchView( new HotelAdminView());

                break;
            case 3:
                ViewManger.switchView(new AdministratorView());
                break;
        }

    }

}
