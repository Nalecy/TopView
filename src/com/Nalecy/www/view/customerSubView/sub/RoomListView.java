package com.Nalecy.www.view.customerSubView.sub;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RoomListView extends Application {
    private static RoomListView instance;
    private RoomListView(){}
    public static RoomListView getInstance(){
        if(instance == null){
            instance = new RoomListView();
        }
        return instance;
    }

    private Stage stage;
    private Scene scene;
    private HBox hBox;
    private VBox vBox;

    //private Button infoButton
   // private Button

    public void display(){

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}
