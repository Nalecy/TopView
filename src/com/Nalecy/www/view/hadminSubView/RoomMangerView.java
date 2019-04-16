package com.Nalecy.www.view.hadminSubView;

import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.HotelAdminView;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomMangerView extends View {
    private Stage window;
    private Scene scene;
    private VBox vBox;
    private HBox buttonHBox;
    private Button backButton;
    private Button addButton;
    private Button deleteButton;
    private Button modifyButton;
    private TableView<Room> roomTableView;

    @Override
    public void display() {
        init();
        setButtonAction();
        window.show();
    }

    private void setButtonAction() {
        backButton.setOnAction(e -> {
            ViewManger.switchView(this, new HotelAdminView());
        });
        addButton.setOnAction(e -> {
            addRoom();
            refresh();
        });
        deleteButton.setOnAction(e -> {
            deleteRoom();
            refresh();
        });
        modifyButton.setOnAction(e -> {
            modifyRoom();
            refresh();
        });
    }
    private void refresh(){
        roomTableView.setItems(getRoomList());
    }
    private void modifyRoom() {
        Room room = roomTableView.getSelectionModel().getSelectedItem();
        List<String> infoList;
        InfoEditPopup modifyPopup = new InfoEditPopup();
        modifyPopup.setInfoNameList("房间名称", "房间类型", "面积", "床宽", "价格");
        modifyPopup.setInfoValueList(room.getName(),String.valueOf(room.getType()),String.valueOf(room.getArea()),String.valueOf(room.getBedWidth()),String.valueOf(room.getPrice()));
        if ((infoList = modifyPopup.display()) != null) {
            room.setName(infoList.get(0));
            room.setType(Integer.valueOf(infoList.get(1)));
            room.setArea(Integer.valueOf(infoList.get(2)));
            room.setBedWidth(Integer.valueOf(infoList.get(3)));
            room.setPrice(Integer.valueOf(infoList.get(4)));
            RoomService.getInstance().saveRoomInfo(room);
        }
    }

    private void deleteRoom() {
        ObservableList<Room> selectRoom;
        selectRoom = roomTableView.getSelectionModel().getSelectedItems();
        for (Room room : selectRoom) {
            RoomService.getInstance().deleteRoom(room.getId());
        }
    }

    private void addRoom() {
        Room room = new Room();
        List<String> infoList;
        InfoEditPopup addRoomPopup = new InfoEditPopup();
        addRoomPopup.setInfoNameList("房间名称", "房间类型", "面积", "床宽", "价格");
        if ((infoList = addRoomPopup.display()) != null) {
            room.setName(infoList.get(0));
            room.setType(Integer.valueOf(infoList.get(1)));
            room.setArea(Integer.valueOf(infoList.get(2)));
            room.setBedWidth(Integer.valueOf(infoList.get(3)));
            room.setPrice(Integer.valueOf(infoList.get(4)));
            RoomService.getInstance().addRoom(room);
        }
    }

    private void init() {
        TableViewCreater<Room> tvc = new TableViewCreater<>();
        tvc.addStringColumn("房间名称", "name", 100);
        tvc.addIntegerColumn("房间类型", "type", 100);
        tvc.addIntegerColumn("面积/米", "area", 100);
        tvc.addIntegerColumn("床宽", "bedWidth", 100);
        tvc.addIntegerColumn("价格", "price", 100);
        roomTableView = tvc.getTableView();
        roomTableView.setItems(getRoomList());

        addButton = new Button("增加");
        deleteButton = new Button("删除");
        modifyButton = new Button("修改");
        backButton = new Button("返回");
        buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(backButton, addButton, deleteButton, modifyButton);
        buttonHBox.setSpacing(50);
        buttonHBox.setAlignment(Pos.CENTER);

        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50));
        vBox.getChildren().addAll(roomTableView, buttonHBox);

        scene = new Scene(vBox);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("所有房间列表");
    }

    private ObservableList<Room> getRoomList() {
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        List<Room> al = RoomService.getInstance().getRoomList(HotelService.getInstance().getCurrentHotel().getId());
        roomList.addAll(al);
        return roomList;
    }


    @Override
    public void close() {
        window.close();
    }
}
