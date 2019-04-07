package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.po.User;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.view.CustomerView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;


public class HotelListView {
    private static HotelListView instance;
    private HotelListView(){}
    public static HotelListView getInstance(){
        if(instance == null){
            instance = new HotelListView();
        }
        return instance;
    }

    private Stage window;
    private Scene scene;
    private VBox vBox;

    private Label label;

    private TableView<Hotel> tableView;
    private TableColumn<Hotel,String> nameColumn;
    private TableColumn<Hotel,Integer> starColumn;
    private TableColumn<Hotel,Double> scoreColumn;
    private TableColumn<Hotel,Integer> numOfScoreColumn;
    private TableColumn<Hotel,String> descriptionColumn;

    private Button enterHotelButton;
    private Button backButton;

    public void display(){
        init();
        serButtonAction();
        window.show();
    }

    private void serButtonAction() {
        enterHotelButton.setOnAction(e -> {
            Hotel hotel = tableView.getSelectionModel().getSelectedItem();
            HotelService.getInstance().setCurrentHotel(hotel);
            System.out.println(hotel);
        });
        backButton.setOnAction(e -> {
            CustomerView.getInstance().display();
            window.close();
        });
    }

    private void init(){
        label = new Label("酒店列表：");

        nameColumn = new TableColumn<>("酒店名字");
        nameColumn.setMinWidth(100);
        nameColumn.setMaxWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        starColumn = new TableColumn<>("星级");
        starColumn.setMinWidth(50);
        starColumn.setMaxWidth(50);
        starColumn.setCellValueFactory(new PropertyValueFactory<>("star"));

        scoreColumn = new TableColumn<>("分数");
        scoreColumn.setMinWidth(50);
        scoreColumn.setMaxWidth(50);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        numOfScoreColumn = new TableColumn<>("评分人数");
        numOfScoreColumn.setMinWidth(100);
        numOfScoreColumn.setMaxWidth(100);
        numOfScoreColumn.setCellValueFactory(new PropertyValueFactory<>("numOfScore"));

        descriptionColumn = new TableColumn<>("描述");
        descriptionColumn.setMinWidth(500);
        descriptionColumn.setMaxWidth(500);
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        tableView = new TableView<>();
        tableView.setMaxSize(800,800);
        tableView.setItems(getList());
        tableView.getColumns().addAll(nameColumn,starColumn,scoreColumn,numOfScoreColumn,descriptionColumn);

        enterHotelButton = new Button("进入选中酒店");
        enterHotelButton.setMinWidth(800);

        backButton = new Button("返回");
        backButton.setMinWidth(800);

        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(60,100,100,100));
        vBox.getChildren().addAll(label,tableView,enterHotelButton,backButton);

        scene = new Scene(vBox,1000,700);
        window = new Stage();
        window.setScene(scene);
        window.setTitle("查看酒店");
        window.setResizable(false);
    }
    private ObservableList<Hotel> getList(){
        ObservableList<Hotel> hotels = FXCollections.observableArrayList();
        ArrayList<Hotel> hotelList = HotelService.getInstance().getHotelList();

        for (Hotel hotel : hotelList) {
            hotels.add(hotel);
        }
        return hotels;
    }
}
