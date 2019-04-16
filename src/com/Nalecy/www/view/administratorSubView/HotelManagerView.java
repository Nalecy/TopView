package com.Nalecy.www.view.administratorSubView;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.AdministratorView;
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

public class HotelManagerView extends View {
    private Stage window;
    private Scene scene;
    private VBox vBox;
    private HBox buttonHBox;

    private Button backButton;
    private Button addButton;
    private Button deleteButton;
    private Button modifyButton;
    private TableView<Hotel> roomTableView;

    @Override
    public void display() {
        init();
        setButtonAction();
        window.show();
    }

    private void setButtonAction() {
        addButton.setOnAction(e->{
            addHotel();
            refresh();
        });
        deleteButton.setOnAction(e->{
            deleteHotel();
            refresh();
        });
        modifyButton.setOnAction(e->{
            modifyHotel();
            refresh();
        });
        backButton.setOnAction(e -> {
            ViewManger.switchView(this,new AdministratorView());
        });
    }
    private void refresh(){
        roomTableView.setItems(getHotelList());
    }
    private void addHotel() {
        Hotel hotel = new Hotel();
        List<String> infoList;
        InfoEditPopup addHotelPopup = new InfoEditPopup();
        addHotelPopup.setInfoNameList("酒店名称", "星级", "描述");
        if ((infoList = addHotelPopup.display()) != null) {
            hotel.setName(infoList.get(0));
            hotel.setStar(Integer.valueOf(infoList.get(1)));
            hotel.setDescription(infoList.get(2));
            HotelService.getInstance().addHotel(hotel);
        }
    }

    private void deleteHotel() {
        ObservableList<Hotel> selectHotel;
        selectHotel = roomTableView.getSelectionModel().getSelectedItems();
        for (Hotel hotel : selectHotel) {
            HotelService.getInstance().deleteHotel(hotel.getId());
        }
    }

    private void modifyHotel() {
        Hotel hotel = roomTableView.getSelectionModel().getSelectedItem();
        if(hotel == null){
            PromptAlert.display("错误","请先选择酒店");
            return;
        }
        List<String> infoList;
        InfoEditPopup modifyPopup = new InfoEditPopup();
        modifyPopup.setInfoNameList("酒店名称", "星级", "描述");
        modifyPopup.setInfoValueList(hotel.getName(),String.valueOf(hotel.getStar()),hotel.getDescription());
        if ((infoList = modifyPopup.display()) != null) {
            hotel.setName(infoList.get(0));
            hotel.setStar(Integer.valueOf(infoList.get(1)));
            hotel.setDescription(infoList.get(2));
            HotelService.getInstance().updateHotel(hotel);
        }
    }

    private void init() {
        TableViewCreater<Hotel> tvc = new TableViewCreater<>();
        tvc.addStringColumn("酒店名称", "name", 100);
        tvc.addIntegerColumn("星级", "star", 50);
        tvc.addIntegerColumn("评分", "score", 50);
        tvc.addIntegerColumn("评分人数", "numOfScore", 50);
        tvc.addIntegerColumn("描述", "description", 250);
        roomTableView = tvc.getTableView();
        roomTableView.setItems(getHotelList());

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

    private ObservableList<Hotel> getHotelList() {
        ObservableList<Hotel> roomList = FXCollections.observableArrayList();
        List<Hotel> al = HotelService.getInstance().getHotelList();
        roomList.addAll(al);
        return roomList;
    }


    @Override
    public void close() {
        window.close();
    }
}