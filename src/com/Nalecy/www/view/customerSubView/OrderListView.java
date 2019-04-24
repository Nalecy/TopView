
package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Order;
import com.Nalecy.www.po.forTableView.OrderT;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.OrderService;
import com.Nalecy.www.util.ButtonCreater;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
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
    private OrderService orderService = ServiceFactory.getOrderService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();


    private Stage window;
    private Scene scene;
    private VBox vBox;
    private HBox hBox;

    private Label finOrderLabel;
    private TableView<OrderT> finTableView;

    private Label unfinOrderLabel;
    private TableView<OrderT> unfinTableView;

    private Label tipLabel;
    private Button commentButton;
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

    @Override
    public void hide() {
        window.hide();
    }

    private void setButtonAction() {
        backButton.setOnAction(e -> {
            ViewManger.back();
        });
        commentButton.setOnAction((e ->{
            commentOrder();
        }));
        cancelOrderButton.setOnAction(e -> {
            cancelOrder();
            refresh();
        });

    }

    private void commentOrder() {

    }

    private void refresh(){
        finTableView.setItems(getFOrderList());
        unfinTableView.setItems(getUFOrderList());
    }

    private void cancelOrder() {
            Order order = unfinTableView.getSelectionModel().getSelectedItem().getOrder();
            if(order == null){
                PromptAlert.display("错误","请检查是否选择");
                return;
            }
            orderService.cancelOrder(order);
    }

    private ObservableList<OrderT> getUFOrderList() {       //获取unfinished订单列表
        ObservableList<OrderT> orders = FXCollections.observableArrayList();
        List<Order> orderList = orderService.getIncompleteOrder(currentRecorder.getCurrentUserName());         //用当前已登录用户名来获取订单
        for (Order order : orderList) {
            orders.add(new OrderT(order));
        }
        return orders;
    }

    private ObservableList<OrderT> getFOrderList() {        //获取finished订单列表
        ObservableList<OrderT> orders = FXCollections.observableArrayList();
        List<Order> orderList = orderService.getCompleteOrder(currentRecorder.getCurrentUserName());   //用当前已登录用户名来获取订单
        for (Order order : orderList) {
            orders.add(new OrderT(order));
        }
        return orders;
    }

    private void init(){
        finOrderLabel = new Label("已完成订单：");

        TableViewCreater<OrderT> tvc = new TableViewCreater<>();
        tvc.addColumn("用户名","userName",100);
        tvc.addColumn("订单日期","date",100);
        tvc.addColumn("房间时段","roomTime",100);
        tvc.addColumn("酒店名字","hotelName",100);
        tvc.addColumn("房间名字","roomName",100);
        tvc.addColumn("金额","balance",50);
        finTableView = tvc.getTableView();
        finTableView.setItems(getFOrderList());

        unfinOrderLabel = new Label("未完成订单：");
        tvc = new TableViewCreater<>();
        tvc.addColumn("用户名","userName",100);
        tvc.addColumn("订单日期","date",100);
        tvc.addColumn("房间时段","roomTime",100);
        tvc.addColumn("酒店名字","hotelName",100);
        tvc.addColumn("房间名字","roomName",100);
        tvc.addColumn("金额","balance",50);
        unfinTableView = tvc.getTableView();
        unfinTableView.setItems(getUFOrderList());

        tipLabel = new Label("您可以：");

        commentButton = ButtonCreater.getNewButton("评价已完成订单",150);
        cancelOrderButton = ButtonCreater.getNewButton("取消未完成订单",150);
        backButton = ButtonCreater.getNewButton("返回",100);

        hBox = new HBox();
        hBox.setSpacing(50);
        hBox.getChildren().addAll(tipLabel,commentButton,cancelOrderButton,backButton);

        vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(finOrderLabel,finTableView,unfinOrderLabel,unfinTableView,hBox);

        scene = new Scene(vBox);
        window = new Stage();
        window.setResizable(false);
        window.setScene(scene);
        window.setTitle("查看订单");
    }
}
