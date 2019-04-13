package com.Nalecy.www.view;

import com.Nalecy.www.po.*;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ProGetter;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterView extends View{
    /*private static RegisterView instance;

    private RegisterView() {
    }

    public static RegisterView getInstance() {
        if (instance == null) {
            instance = new RegisterView();
        }
        return instance;
    }*/

    //布局要素
    private Stage window;
    private Scene scene;
    private GridPane grid;

    private ToggleButton customerRegBut;
    private ToggleButton HAdminRegBut;
    private ToggleButton AdministratorRegBut;
    private ToggleGroup group;
    private HBox toggleHBox;

    private Label userLabel;
    private Label passwordLabel;
    private Label phoneLabel;
    private Label idNumberLabel;
    private Label hotelNameLabel;
    private Label resNumLabel;

    private TextField userText;
    private PasswordField passwordText;
    private TextField phoneText;
    private TextField idNumberText;
    private TextField hotelNameText;
    private TextField resNumberText;

    private Button resButton;
    private Button backButton;

    @Override
    public void display() {
        init();
        setButtonAction();
        window.show();

    }

    @Override
    public void close() {
        window.close();
    }

    private void setButtonAction() {
        HAdminRegBut.setSelected(true);

        customerRegBut.setOnAction(e -> {
            hotelNameText.setDisable(true);
            resNumberText.setDisable(true);
        });
        HAdminRegBut.setOnAction(e -> {
            hotelNameText.setDisable(false);
            resNumberText.setDisable(false);
        });
        AdministratorRegBut.setOnAction(e -> {
            hotelNameText.setDisable(true);
            resNumberText.setDisable(false);
        });

        backButton.setOnAction(e -> {
            ViewManger.switchView(this,new LoginView());
        });

        resButton.setOnAction(e -> {
            boolean success = false;
            if (customerRegBut.isSelected()) {  //选择的是顾客
                success = save();
            } else if (HAdminRegBut.isSelected()) {//选择的是酒管
                if (checkPermission("HotelAdmin", resNumberText.getText())) {
                    success = save();
                } else {
                    PromptAlert.display("错误", "注册码错误！");
                }
            } else if (AdministratorRegBut.isSelected()) {//选择的是超管
                if (checkPermission("Administrator", resNumberText.getText())) {
                    success = save();
                } else {
                    PromptAlert.display("错误", "注册码错误！");
                }
            }
            if(success){
                PromptAlert.display("成功","注册成功");
                ViewManger.switchView(this,new LoginView());
            }
            else PromptAlert.display("错误","注册失败");
            clearText();
        });
    }

    private void clearText() {
        userText.clear();
        passwordText.clear();
        phoneText.clear();
        idNumberText.clear();
        hotelNameText.clear();
        resNumberText.clear();
    }

    private boolean save() {
        Person person;

        if (group.getSelectedToggle() == customerRegBut) {
            person = new Customer();
        }
        else if (group.getSelectedToggle() == HAdminRegBut){
            Hotel hotel = HotelService.getInstance().getHotel(hotelNameText.getText());
            if (hotel == null)return false;
            person = new HotelAdmin();
            ((HotelAdmin) person).setHotelID(hotel.getId());
        }
        else {
            person = new Administrator();
        }
        person.setUserName(userText.getText());
        person.setPassword(passwordText.getText());
        person.setIdNumber(idNumberText.getText());
        person.setTelephone(phoneText.getText());
        PersonService.getInstance().savePersonInfo(person);
        return true;
    }

    private void init() {
        customerRegBut = new ToggleButton("顾客");
        HAdminRegBut = new ToggleButton("酒管");
        AdministratorRegBut = new ToggleButton("超管");

        group = new ToggleGroup();
        group.getToggles().addAll(customerRegBut, HAdminRegBut, AdministratorRegBut);

        userLabel = new Label("用户名：");
        passwordLabel = new Label("密码：");
        phoneLabel = new Label("电话：");
        idNumberLabel = new Label("身份证：");
        hotelNameLabel = new Label("酒店名字：");
        resNumLabel = new Label("注册码：");

        userText = new TextField();
        passwordText = new PasswordField();
        resNumberText = new TextField();
        phoneText = new TextField();
        idNumberText = new TextField();
        hotelNameText = new TextField();

        userText.setPromptText("请输入用户名");
        phoneText.setPromptText("请输入电话");
        idNumberText.setPromptText("请输入身份证");
        hotelNameText.setPromptText("请输入酒店名字");
        resNumberText.setPromptText("请输入注册码");

        userText.setMinWidth(80);
        passwordText.setMinWidth(80);
        phoneText.setMinWidth(80);
        idNumberText.setMinWidth(80);
        hotelNameText.setMinWidth(80);
        resNumberText.setMinWidth(80);

        resButton = new Button("注册");
        backButton = new Button("返回");

        toggleHBox = new HBox();
        toggleHBox.getChildren().addAll(customerRegBut, HAdminRegBut, AdministratorRegBut);
        toggleHBox.setSpacing(10);

        grid = new GridPane();
        GridPane.setConstraints(toggleHBox, 1, 0);
        GridPane.setConstraints(userLabel, 0, 1);
        GridPane.setConstraints(userText, 1, 1);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordText, 1, 2);
        GridPane.setConstraints(phoneLabel, 0, 3);
        GridPane.setConstraints(phoneText, 1, 3);
        GridPane.setConstraints(idNumberLabel, 0, 4);
        GridPane.setConstraints(idNumberText, 1, 4);
        GridPane.setConstraints(hotelNameLabel, 0, 5);
        GridPane.setConstraints(hotelNameText, 1, 5);
        GridPane.setConstraints(resNumLabel, 0, 6);
        GridPane.setConstraints(resNumberText, 1, 6);
        GridPane.setConstraints(resButton, 0, 7);
        GridPane.setConstraints(backButton, 2, 7);
        grid.setHgap(30);
        grid.setVgap(30);
        grid.getChildren().addAll(toggleHBox, userLabel, userText, passwordLabel, passwordText, phoneLabel, phoneText, idNumberLabel, idNumberText, hotelNameLabel, hotelNameText, resNumLabel, resNumberText, resButton, backButton);
        grid.setPadding(new Insets(20, 20, 20, 20));

        scene = new Scene(grid);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("注册");
    }

    private boolean checkPermission(String s, String input) {
        return ProGetter.getInstance().get(s).equals(input);

    }
}
/*class InfoGetView {
    private static InfoGetView instance;
    private InfoGetView(){}
    public static InfoGetView getInstance(){
        if(instance == null){
            instance = new InfoGetView();
        }
        return instance;
    }

    private Stage window;
    private Scene scene;
    private HBox hBox;
    private VBox labelVBox;
    private VBox textVBox;

    private Label userNameLabel;
    private Label passwordLabel;
    private Label phoneLabel;
    private Label idNumberLabel;

    private TextField userNameText;
    private TextField passwordText;
    private TextField phoneText;
    private TextField idNumberText;

    private Button resButton;
    private Button backButton;

    public void display(){
        init();
        window.show();
    }

    private void init(){
        userNameLabel = new Label("用户名：");
        passwordLabel = new Label("密码：");
        phoneLabel = new Label("电话：");
        idNumberLabel = new Label("身份证：");

        userNameText = new TextField();
        userNameText.setPromptText("请输入用户名");
        passwordText = new TextField();
        passwordText.setPromptText("请输入密码");
        phoneText = new TextField();
        phoneText.setPromptText("请输入电话");
        idNumberText = new TextField();
        idNumberText.setPromptText("请输入身份证");

        resButton = new Button("注册");
        backButton = new Button("返回");

        labelVBox = new VBox();
        labelVBox.setSpacing(10);
        labelVBox.getChildren().addAll(userNameLabel, passwordLabel, phoneLabel, idNumberLabel,resButton);

        textVBox = new VBox();
        textVBox.setSpacing(10);
        textVBox.getChildren().addAll(userNameText, passwordText, phoneText, idNumberText,backButton);

        hBox = new HBox();
        hBox.setSpacing(5);
        hBox.getChildren().addAll(labelVBox,textVBox);

        scene = new Scene(hBox);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("注册用户");
        window.initModality(Modality.APPLICATION_MODAL);
    }
}*/
