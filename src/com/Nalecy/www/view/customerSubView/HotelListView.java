package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.po.Hotel;
import com.Nalecy.www.service.HotelService;
import com.Nalecy.www.service.Impl.HotelServiceImpl;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;


public class HotelListView extends View {
    private HotelService hotelService = ServiceFactory.getHotelService();

    private Stage window;
    private Scene scene;
    private VBox vBox;

    private Label label;

    private TableView<Hotel> tableView;

    private Button enterHotelButton;
    private Button backButton;

    @Override
    public void display(){
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
        enterHotelButton.setOnAction(e -> {
            Hotel hotel = tableView.getSelectionModel().getSelectedItem();
            if(hotel == null) {
                PromptAlert.display("错误","未选择酒店");
                return;
            }
            hotelService.setCurrentHotel(hotel);
            ViewManger.switchView( new RoomListView());

        });
        backButton.setOnAction(e -> {
            ViewManger.back();
        });
    }

    private void init(){
        label = new Label("酒店列表：");

        TableViewCreater<Hotel> tvc = new TableViewCreater<>();
        tvc.addStringColumn("酒店名字","name",100);
        tvc.addIntegerColumn("星级","star",50);
        tvc.addDoubleColumn("分数","score",50);
        tvc.addIntegerColumn("评分人数","numOfScore",100);
        tvc.addStringColumn("描述","description",500);

        tableView = tvc.getTableView();
        tableView.setMaxSize(800,800);
        tableView.setItems(getList());

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
        List<Hotel> hotelList = hotelService.getHotelList();

        for (Hotel hotel : hotelList) {
            hotels.add(hotel);
        }
        return hotels;
    }
}
