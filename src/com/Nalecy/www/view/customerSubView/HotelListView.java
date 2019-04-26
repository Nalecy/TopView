package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.util.ComponentCreater;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import com.Nalecy.www.view.customerSubView.sub.RoomListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class HotelListView extends View {
    private HotelService hotelService = ServiceFactory.getHotelService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();

    private Stage window;
    private Scene scene;
    private VBox vBox;

    private Label label;
    private TextField searchField;

    private TableView<Hotel> tableView;

    private Button enterHotelButton;
    private Button backButton;

    @Override
    public void display() {
        if (!hasInit) {
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
        enterHotelButton.setOnAction(e -> {
            Hotel hotel = tableView.getSelectionModel().getSelectedItem();
            if (hotel == null) {
                PromptAlert.display("错误", "未选择酒店");
                return;
            }
            currentRecorder.setCurrentHotelId(hotel.getId());
            ViewManger.switchView(new RoomListView());

        });
        backButton.setOnAction(e -> {
            ViewManger.back();
        });
        //设置搜索框监听
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            //如果文本框为空 默认不搜索，显示所有酒店
            if (newValue.length() == 0) {
                tableView.setItems(getList());
            } else {
                List<Hotel> hotels = hotelService.fuzzySearchHotel(searchField.getText());
                ObservableList<Hotel> hotels1 = FXCollections.observableArrayList();
                if (hotels != null) {
                    hotels1.addAll(hotels);
                }
                tableView.setItems(hotels1);

            }
        });
    }
    /** 初始化布局元素 */
    private void init() {
        label = new Label("酒店列表：");

        //初始化搜索框
        searchField = new TextField();
        searchField.setPromptText("请在此输入酒店名字");
        searchField.setMinWidth(800);
        searchField.setMaxWidth(800);

        //初始化表
        TableViewCreater<Hotel> tvc = new TableViewCreater<>();
        tvc.addColumn("酒店名字", "name", 100);
        tvc.addColumn("星级", "star", 50);
        tvc.addColumn("分数", "score", 50);
        tvc.addColumn("评分人数", "numOfScore", 100);
        tvc.addColumn("描述", "description", 500);

        tableView = tvc.getTableView();
        tableView.setMaxSize(800, 800);
        tableView.setItems(getList());

        //初始化按钮
        enterHotelButton = ComponentCreater.newButton("进入选中酒店", 800);
        backButton = ComponentCreater.newButton("返回", 800);

        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(60, 100, 100, 100));
        vBox.getChildren().addAll(searchField, label, tableView, enterHotelButton, backButton);

        scene = new Scene(vBox, 1000, 700);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("查看酒店");
        window.setResizable(false);
    }

    private ObservableList<Hotel> getList() {
        ObservableList<Hotel> hotels = FXCollections.observableArrayList();
        List<Hotel> hotelList = hotelService.getHotelList();

        hotels.addAll(hotelList);
        return hotels;
    }
}
