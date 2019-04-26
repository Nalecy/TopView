package com.Nalecy.www.view.hadminSubView;

import com.Nalecy.www.po.Room;
import com.Nalecy.www.service.CurrentRecorder;
import com.Nalecy.www.service.RoomService;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.*;
import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import com.Nalecy.www.view.popupUtil.PromptAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class RoomMangerView extends View {
    private RoomService roomService = ServiceFactory.getRoomService();
    private CurrentRecorder currentRecorder = ServiceFactory.getCurrentRecorder();

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
        if(!hasInit) {
            init();
            setButtonAction();
            hasInit = true;
        }
        window.show();
    }

    private void setButtonAction() {
        backButton.setOnAction(e -> {
            ViewManger.back();
        });
        addButton.setOnAction(e -> {
            try{
                addRoom();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
            refresh();
        });
        deleteButton.setOnAction(e -> {
            try{
                deleteRoom();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
            refresh();
        });
        modifyButton.setOnAction(e -> {
            try{
                modifyRoom();
            }catch (Exception exception){
                PromptAlert.display("错误", "出现未知错误");
                exception.printStackTrace();
            }
            refresh();
        });
    }
    //数据刷新
    private void refresh(){
        roomTableView.setItems(getRoomList());
    }

    private void modifyRoom() {
        Room room = roomTableView.getSelectionModel().getSelectedItem();
        //如果未选择房间
        if(room == null){
            PromptAlert.display("错误","未选择房间");
            return;
        }
        List<String> infoList;
        InfoEditPopup modifyPopup = new InfoEditPopup();
        modifyPopup.setInfoNameList("房间名称", "房间类型", "面积", "床宽", "价格");
        modifyPopup.setInfoValueList(room.getName(),String.valueOf(room.getType()),String.valueOf(room.getArea()),String.valueOf(room.getBedWidth()),String.valueOf(room.getPrice()));
        if ((infoList = modifyPopup.display()) != null) {
            //正则判断
            if(!RegexUtil.isZh(infoList.get(0))){ PromptAlert.display("错误","检查房间名称输入");return;}
            if(!RegexUtil.isOneToThree(infoList.get(1))){PromptAlert.display("错误","检查房间类型输入");return;}
            if(!RegexUtil.isNumber(infoList.get(2))){PromptAlert.display("错误","检查面积输入");return;}
            if(!RegexUtil.isNumber(infoList.get(3))){PromptAlert.display("错误","检查床宽输入");return;}
            if(!RegexUtil.isNumber(infoList.get(4))){PromptAlert.display("错误","检查价格输入");return;}
            //组装room对象
            room.setName(infoList.get(0));
            room.setType(Integer.valueOf(infoList.get(1)));
            room.setArea(Integer.valueOf(infoList.get(2)));
            room.setBedWidth(Integer.valueOf(infoList.get(3)));
            room.setPrice(Integer.valueOf(infoList.get(4)));
            roomService.saveRoomInfo(room);
        }
    }

    private void deleteRoom() {
        ObservableList<Room> selectRoom;
        selectRoom = roomTableView.getSelectionModel().getSelectedItems();
        if(selectRoom.size() == 0){
            PromptAlert.display("错误","未选择房间");
            return;
        }
        for (Room room : selectRoom) {
            roomService.deleteRoom(room.getId());
        }
    }

    private void addRoom() {
        Room room = new Room();
        List<String> infoList;
        InfoEditPopup addRoomPopup = new InfoEditPopup();
        addRoomPopup.setInfoNameList("房间名称", "房间类型", "面积", "床宽", "价格");
        if ((infoList = addRoomPopup.display()) != null) {
            //正则判断
            if(!RegexUtil.isZh(infoList.get(0))){ PromptAlert.display("错误","检查房间名称输入");return;}
            if(!RegexUtil.isOneToThree(infoList.get(1))){PromptAlert.display("错误","检查房间类型输入");return;}
            if(!RegexUtil.isNumber(infoList.get(2))){PromptAlert.display("错误","检查房间面积输入");return;}
            if(!RegexUtil.isNumber(infoList.get(3))){PromptAlert.display("错误","检查床宽输入");return;}
            if(!RegexUtil.isNumber(infoList.get(4))){PromptAlert.display("错误","检查价格输入");return;}


            room.setName(infoList.get(0));
            room.setType(Integer.valueOf(infoList.get(1)));
            room.setArea(Integer.valueOf(infoList.get(2)));
            room.setBedWidth(Integer.valueOf(infoList.get(3)));
            room.setPrice(Integer.valueOf(infoList.get(4)));
            roomService.addRoom(room);
        }
    }
    /** 初始化布局元素 */
    private void init() {
        TableViewCreater<Room> tvc = new TableViewCreater<>();
        tvc.addColumn("房间名称", "name", 100);
        tvc.addColumn("房间类型", "type", 100);
        tvc.addColumn("面积/米", "area", 100);
        tvc.addColumn("床宽", "bedWidth", 100);
        tvc.addColumn("价格", "price", 100);
        roomTableView = tvc.getTableView();
        roomTableView.setItems(getRoomList());

        addButton = ComponentCreater.newButton("增加");
        deleteButton = ComponentCreater.newButton("删除");
        modifyButton = ComponentCreater.newButton("修改");
        backButton = ComponentCreater.newButton("返回");
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
        window.setResizable(false);
        window.setTitle("所有房间列表");
    }

    private ObservableList<Room> getRoomList() {
        ObservableList<Room> roomList = FXCollections.observableArrayList();
        List<Room> list = roomService.getRoomList(currentRecorder.getCurrentHotelId());
        if(list != null)
            roomList.addAll(list);
        return roomList;
    }


    @Override
    public void close() {
        window.close();
    }

    @Override
    public void hide() {
        window.hide();
    }
}
