package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.Order;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.view.alert.PromptAlert;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;

public class OrderListView {
    private static OrderListView instance;
    private OrderListView(){}
    public static OrderListView getInstance(){
        if(instance == null){
            instance = new OrderListView();
        }
        return instance;
    }
    private Stage window;
    private Scene scene;
    private VBox vBox;
    private HBox hBox;

    private Label finOrderLabel;
    private TableView<Order> finTableView;

    private Label unfinOrderLabel;
    private TableView<Order> unfinTableView;

    private Label tipLabel;
    private Button cancelOrderButton;
    private Button backButton;

    public void display(){
        init();
        window.show();
    }
    private void init(){
        finOrderLabel = new Label("已完成订单：");

        TableViewCreater<Order> tvc = new TableViewCreater<>();
        tvc.addStringColumn("用户名","userName",100);
        tvc.addDateColumn("订单日期","date",100);
        tvc.addIntegerColumn("房间时段","roomPeriod",100);
        tvc.addIntegerColumn("酒店id","hotelID",100);
        finTableView = tvc.getTableView();
        finTableView.setItems(getFOrderList());

        unfinOrderLabel = new Label("未完成订单：");
        tvc = new TableViewCreater<>();
        tvc.addStringColumn("用户名","userName",100);
        tvc.addDateColumn("订单日期","date",100);
        tvc.addIntegerColumn("房间时段","roomPeriod",100);
        tvc.addIntegerColumn("酒店id","hotelID",100);
        unfinTableView = tvc.getTableView();
        unfinTableView.setItems(getUFOrderList());

        cancelOrderButton = new Button("取消订单");
        cancelOrderButton.setMinWidth(100);

        backButton = new Button("返回");
        backButton.setMinWidth(100);

        hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(cancelOrderButton,backButton);

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(finOrderLabel,finTableView,unfinOrderLabel,unfinTableView,hBox);

        scene = new Scene(vBox);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("查看订单");
    }

    private ObservableList<Order> getUFOrderList() {
        return null;
    }

    private ObservableList<Order> getFOrderList() {
        return null;
    }
}
