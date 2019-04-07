package com.Nalecy.www.view.alert;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PromptAlert {
    private static Stage window;
    private static Scene scene;
    private static VBox vBox;

    private static Label messageLabel;
    private static Button yesButton;
    public static void display(String title, String message){
        init(title, message);
        yesButton.setOnAction(e ->{
            window.close();
        });
        window.showAndWait();

    }
    private static void init(String title, String message){
        yesButton = new Button("确定");
        messageLabel = new Label(message);

        yesButton.setMinWidth(40);

        vBox = new VBox();
        vBox.setPadding(new Insets(30,10,40,100));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(messageLabel,yesButton);

        scene = new Scene(vBox,250,100);
        window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
    }
}
