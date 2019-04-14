package com.Nalecy.www.view.customerSubView.sub;


import com.Nalecy.www.constantClass.RoomPeriod;
import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.DateService;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import com.Nalecy.www.view.customerSubView.HotelListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class RoomListView extends View {
    private Stage stage;
    private Scene scene;
    private HBox hBox;
    private VBox leftVBox;
    private VBox rightVBox;

    private TableView<Room> roomListTable;

    private Label label;
    private ChoiceBox<String> dateChoiceBox;
    private ChoiceBox<String> timeChoiceBox;
    private Button reserveButton;
    private Button backButton;

    //private Button infoButton
   // private Button

    public void display(){
        init();
        setButtonAction();
        stage.show();
    }

    @Override
    public void close() {
        stage.close();
    }

    private void setButtonAction() {
        reserveButton.setOnAction(e -> {
           reserve();
        });
        backButton.setOnAction(e -> {
            ViewManger.switchView(this, new HotelListView());
        });
    }

    private void init(){
        label = new Label("房间列表：");

        TableViewCreater<Room> tvc = new TableViewCreater<>();
        tvc.addStringColumn("房间名称","name",100);
        tvc.addIntegerColumn("房间类型","type",100);
        tvc.addIntegerColumn("面积/米","area",100);
        tvc.addIntegerColumn("床宽","bedWidth",100);
        tvc.addIntegerColumn("价格","price",100);
        roomListTable = tvc.getTableView();
        roomListTable.setItems(getRoomList());


        label = new Label("房间列表：");
        leftVBox = new VBox();
        leftVBox.getChildren().addAll(label,roomListTable);
        leftVBox.setPadding(new Insets(50));

        //右边
        dateChoiceBox = new ChoiceBox<>();
        dateChoiceBox.setItems(FXCollections.observableArrayList("明天","后天","大后天"));
        dateChoiceBox.setValue("明天");
        dateChoiceBox.setMinWidth(200);

        timeChoiceBox = new ChoiceBox<>();
        timeChoiceBox.setItems(FXCollections.observableArrayList("上午","下午","晚上"));
        timeChoiceBox.setValue("上午");
        timeChoiceBox.setMinWidth(200);

        reserveButton = new Button("预定");
        reserveButton.setMinWidth(200);

        backButton = new Button("返回");
        backButton.setMinWidth(200);

        rightVBox = new VBox();
        rightVBox.getChildren().addAll(dateChoiceBox,timeChoiceBox,reserveButton,backButton);
        rightVBox.setSpacing(100);
        rightVBox.setPadding(new Insets(50));

        //
        hBox = new HBox();
        hBox.getChildren().addAll(leftVBox,rightVBox);
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(50));

        scene = new Scene(hBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("酒店内部");
    }
    private ObservableList<Room> getRoomList(){
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        List<Room> al = RoomService.getInstance().getRoomList(HotelService.getInstance().getCurrentHotel().getId());
        roomList.addAll(al);
        return roomList;
    }
    private void reserve(){
        Integer date = null;
        Integer time = null;
        String dateValue = dateChoiceBox.getValue();
        String timeValue = timeChoiceBox.getValue();
        Room room = roomListTable.getSelectionModel().getSelectedItem();
        if (room == null){
            PromptAlert.display("错误","未选择房间");
            return;
        }
        switch (dateValue) {
            case "明天": date = RoomPeriod.ONE_DAY_LATER;break;  //根据选修设定是几天后
            case "后天": date = RoomPeriod.TWO_DAY_LATER;break;
            case "大后天": date = RoomPeriod.THREE_DAY_LATER;break;
        }
        switch (timeValue){
            case "上午": time = RoomPeriod.MORNING;break;
            case "下午": time = RoomPeriod.AFTERNOON;break;
            case "晚上": time = RoomPeriod.NIGHT;break;
        }
        RoomService.getInstance().setCurrentRoom(room);
        if( RoomService.getInstance().reserve(DateService.getInstance().getOneDay(date),time) ) PromptAlert.display("提示","预定成功");
        else PromptAlert.display("错误","预定失败,可能该时段已经被预定了");
    }
}
