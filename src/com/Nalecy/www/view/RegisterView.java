package com.Nalecy.www.view;

import com.Nalecy.www.po.*;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.PersonService;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.*;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class RegisterView extends View{
    private HotelService hotelService = ServiceFactory.getHotelService();
    private PersonService personService = ServiceFactory.getPersonService();

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
        if(!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        window.show();
    }

    @Override
    public void close() {
        window.close();
    }

    @Override
    public void hide() {
        window.hide();
    }

    private void setButtonAction() {
        HAdminRegBut.setSelected(true);

        customerRegBut.setOnAction(e -> {
            //若选择了顾客，则禁用酒店名字栏和注册码一栏
            hotelNameText.setDisable(true);
            resNumberText.setDisable(true);
        });
        HAdminRegBut.setOnAction(e -> {
            //若选择了酒管，则启用酒店名字栏和注册码一栏
            hotelNameText.setDisable(false);
            resNumberText.setDisable(false);
        });
        AdministratorRegBut.setOnAction(e -> {
            //若选择了超管，则禁用酒店名字栏吗，启用注册码一栏
            hotelNameText.setDisable(true);
            resNumberText.setDisable(false);
        });

        backButton.setOnAction(e -> {
            //返回
            ViewManger.back();
        });

        resButton.setOnAction(e -> {
            boolean success = false;
            if (customerRegBut.isSelected()) {  //选择的是顾客
                success = save();//直接返回是否注册成功
            } else if (HAdminRegBut.isSelected()) {
                //选择的是酒管
                //先检查注册码是否正确
                if (checkPermission("HotelAdmin", resNumberText.getText())) {
                    success = save();
                } else {
                    //注册码错误
                    PromptAlert.display("错误", "注册码错误！");
                }
            } else if (AdministratorRegBut.isSelected()) {
                //选择的是超管
                //先检查注册码是否正确
                if (checkPermission("Administrator", resNumberText.getText())) {
                    success = save();
                } else {
                    //注册码错误
                    PromptAlert.display("错误", "注册码错误！");
                }
            }
            if(success){
                PromptAlert.display("成功","注册成功");
                ViewManger.back();
            }
            else PromptAlert.display("错误","注册失败");
            clearText();
        });
    }

    /**
     * 清除文本框内容
     */
    private void clearText() {
        userText.clear();
        passwordText.clear();
        phoneText.clear();
        idNumberText.clear();
        hotelNameText.clear();
        resNumberText.clear();
    }

    private boolean save() {
        //正则判断
        if(!RegexUtil.isUserName(userText.getText())){PromptAlert.display("错误","检查用户名输入");return false;}
        if(!RegexUtil.isPassword(passwordText.getText())){PromptAlert.display("错误","检查密码输入");return false;}
        if(!RegexUtil.isIdCard(idNumberText.getText())){PromptAlert.display("错误","检查身份证输入");return false;}
        if(!RegexUtil.isTelephone(phoneText.getText())){PromptAlert.display("错误","检查手机号输入");return false;}
        //判断用户名是否已存在
        if(personService.searchPerson(userText.getText()) != null){
            PromptAlert.display("错误","用户名已存在");
            return false;
        }
        //开始保存
        Person person;
        //判断所选身份
        if (group.getSelectedToggle() == customerRegBut) {
            //如果选择是顾客
            person = new Customer();
        }
        else if (group.getSelectedToggle() == HAdminRegBut){
            //如果选择是酒管
            //通过酒店名字获取酒店对象判断是否存在
            Hotel hotel = hotelService.getHotel(hotelNameText.getText());
            if (hotel == null)return false;//不存在返回失败
            person = new HotelAdmin();
            ((HotelAdmin) person).setHotelID(hotel.getId());
        }
        else {
            //如果选择是超管
            person = new Administrator();
        }
        //组装person对象
        person.setUserName(userText.getText());
        person.setPassword(passwordText.getText());
        person.setIdNumber(idNumberText.getText());
        person.setTelephone(phoneText.getText());
        personService.addPerson(person);
        return true;
    }
    /** 初始化布局元素 */
    private void init() {
        //初始化 选择按钮组
        customerRegBut = new ToggleButton("顾客");
        HAdminRegBut = new ToggleButton("酒管");
        AdministratorRegBut = new ToggleButton("超管");

        group = new ToggleGroup();
        group.getToggles().addAll(customerRegBut, HAdminRegBut, AdministratorRegBut);

        //初始化选择按钮组横向容器
        toggleHBox = new HBox();
        toggleHBox.getChildren().addAll(customerRegBut, HAdminRegBut, AdministratorRegBut);
        toggleHBox.setSpacing(10);

        //初始化文本框前的标签
        userLabel = ComponentCreater.newLabel("用户名：");
        passwordLabel = ComponentCreater.newLabel("密码：");
        phoneLabel = ComponentCreater.newLabel("电话：");
        idNumberLabel = ComponentCreater.newLabel("身份证：");
        hotelNameLabel = ComponentCreater.newLabel("酒店名字：");
        resNumLabel = ComponentCreater.newLabel("注册码：");

        //初始化文本框
        userText = ComponentCreater.newTextField("允许5-12位字母数字");
        passwordText = ComponentCreater.newPasswordField("允许6-12位字母数字");
        phoneText = ComponentCreater.newTextField("允许11位数字");
        idNumberText = ComponentCreater.newTextField("允许18位数身份证");
        hotelNameText = ComponentCreater.newTextField("仅允许汉字");
        resNumberText = ComponentCreater.newTextField("请输入注册码");

        //初始化按钮
        resButton = ComponentCreater.newButton("注册");
        backButton = ComponentCreater.newButton("返回");

        //初始化网格布局
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

        //初始化窗口属性
        scene = new Scene(grid);
        window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("注册");
    }

    /**+
     * 检查注册码是否正确
     * @param identify 身份的名字 如:HotelAdmin
     * @param input 注册码输入
     * @return true/false
     */
    private boolean checkPermission(String identify, String input) {
        return ProGetter.getInstance().get(identify).equals(input);
    }
}