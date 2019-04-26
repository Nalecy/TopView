package com.Nalecy.www.view.popupUtil;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 用于二次确认的弹窗工具
 */
public final class ConfirmAlert {
    //防止实例化
    private ConfirmAlert(){
        throw new RuntimeException("请勿实例化ConfirmAlert");
    }
    private static Stage window;
    private static Scene scene;
    private static VBox vBox;
    private static HBox hBox;

    private static Label messageLabel;
    private static Button yesButton;
    private static Button noButton;
    private static boolean res = false;

    /**
     * 传入弹窗标题 弹窗内容 且展示
     * @param title 标题
     * @param message 内容
     * @return 是否确认
     */
    public static boolean display(String title, String message){
        if(title == null||message == null){
            throw new NullPointerException();
        }
        init(title, message);
        setButtonAction();
        window.showAndWait();
        return res;
    }

    private static void setButtonAction() {
        yesButton.setOnAction(e ->{
            res = true;
            window.close();
        });
        noButton.setOnAction(d ->{
            res = false;
            window.close();
        });
    }

    /** 初始化布局元素 */
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

        scene = new Scene(vBox,260,130);
        window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);
        window.setScene(scene);
    }
}
