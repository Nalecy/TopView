package com.Nalecy.www.view.popupUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * 用于显示提示文本的弹窗工具
 */
public final class PromptAlert {
    //防止实例化
    private PromptAlert(){
        throw new RuntimeException("请勿实例化PromptAlert");
    }
    private static Stage window;
    private static Scene scene;
    private static VBox vBox;

    private static Label messageLabel;
    private static Button yesButton;

    /**
     * 传入弹窗标题 弹窗内容 且展示
     * @param title 标题
     * @param message 内容
     */
    public static void display(String title, String message){
        if(title == null||message == null){
            throw new NullPointerException();
        }
        init(title, message);
        yesButton.setOnAction(e ->{
            window.close();
        });
        window.showAndWait();

    }

    /** 初始化布局元素 */
    private static void init(String title, String message){
        yesButton = new Button("确定");
        messageLabel = new Label(message);
        messageLabel.setWrapText(true);

        yesButton.setMinWidth(40);

        vBox = new VBox();
        vBox.setPadding(new Insets(10,10,10,10));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        vBox.setMaxWidth(250);
        vBox.setMinWidth(250);
        vBox.setMinHeight(100);
        vBox.getChildren().addAll(messageLabel,yesButton);


        scene = new Scene(vBox);
        window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
    }
}
