package com.Nalecy.www.view.alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmAlert {
    private static Stage window;
    private static Scene scene;
    private static VBox vBox;
    private static HBox hBox;

    private static Label messageLabel;
    private static Button yesButton;
    private static Button noButton;
    private static boolean res = false;
    public static boolean display(String title, String message){
        init(title, message);
        yesButton.setOnAction(e ->{
            res = true;
            window.close();
        });
        noButton.setOnAction(d ->{
            res = false;
            window.close();
        });
        window.showAndWait();
        return res;
    }
    private static void init(String title, String message){
        yesButton = new Button("确定");
        noButton = new Button("取消");
        messageLabel = new Label(message);

        yesButton.setMinWidth(40);
        noButton.setMinWidth(40);

        hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(yesButton,noButton);

        vBox = new VBox();
        vBox.setPadding(new Insets(40,40,40,80));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(messageLabel,hBox);

        scene = new Scene(vBox,260,180);
        window = new Stage();
        window.setTitle(title);
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
    }
}
