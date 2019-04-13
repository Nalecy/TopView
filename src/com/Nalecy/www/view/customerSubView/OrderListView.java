
package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.forTableView.OrderT;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.CustomerView;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class OrderListView extends View {
    /*private static OrderListView instance;
    private OrderListView(){}
    public static OrderListView getInstance(){
        if(instance == null){
            instance = new OrderListView();
        }
        return instance;
    }*/
    private Stage window;
    private Scene scene;
    private VBox vBox;
    private HBox hBox;

    private Label finOrderLabel;
    private TableView<OrderT> finTableView;

    private Label unfinOrderLabel;
    private TableView<OrderT> unfinTableView;

    private Label tipLabel;
    private Button cancelOrderButton;
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

    private void setButtonAction() {
        backButton.setOnAction(e -> {
            ViewManger.switchView(this,new CustomerView());
        });
        cancelOrderButton.setOnAction(e -> {
            cancelOrder();
        });

    }

    private void cancelOrder() {
        try {
            Order order = unfinTableView.getSelectionModel().getSelectedItem().getOrder();
            OrderService.getInstance().cancelOrder(order);
        }catch (NullPointerException e){
            PromptAlert.display("错误","请检查是否选择");
        }


    }

    private void init(){
        finOrderLabel = new Label("已完成订单：");

        TableViewCreater<OrderT> tvc = new TableViewCreater<>();
        tvc.addStringColumn("用户名","userName",100);
        tvc.addDateColumn("订单日期","date",100);
        tvc.addIntegerColumn("房间时段","roomTime",100);
        tvc.addStringColumn("酒店名字","hotelName",100);
        tvc.addStringColumn("房间名字","roomName",100);
        finTableView = tvc.getTableView();
        finTableView.setItems(getFOrderList());

        unfinOrderLabel = new Label("未完成订单：");
        tvc = new TableViewCreater<>();
        tvc.addStringColumn("用户名","userName",100);
        tvc.addDateColumn("订单日期","date",100);
        tvc.addIntegerColumn("房间时段","roomTime",100);
        tvc.addStringColumn("酒店名字","hotelName",100);
        tvc.addStringColumn("房间名字","roomName",100);
        unfinTableView = tvc.getTableView();
        unfinTableView.setItems(getUFOrderList());

        cancelOrderButton = new Button("取消订单");
        cancelOrderButton.setMinWidth(100);

        backButton = new Button("返回");
        backButton.setMinWidth(100);

        tipLabel = new Label("您可以：");

        hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(tipLabel,cancelOrderButton,backButton);

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

    private ObservableList<OrderT> getUFOrderList() {       //获取unfinished订单列表
        ObservableList<OrderT> orders = FXCollections.observableArrayList();
        List<Order> orderList = OrderService.getInstance().getIncompleteOrder(HotelService.getInstance().getCurrentUser());         //用当前已登录用户名来获取订单
        for (Order order : orderList) {
            orders.add(new OrderT(order));
        }
        return orders;
    }

    private ObservableList<OrderT> getFOrderList() {        //获取finished订单列表
        ObservableList<OrderT> orders = FXCollections.observableArrayList();
        List<Order> orderList = OrderService.getInstance().getCompleteOrder(HotelService.getInstance().getCurrentUser());   //用当前已登录用户名来获取订单
        for (Order order : orderList) {
            orders.add(new OrderT(order));
        }
        return orders;
    }
}
