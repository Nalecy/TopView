package com.Nalecy.www.view.popupUtil;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

/**
 * 用于便捷构建多行编辑框的可实例化的弹窗
 */
public class InfoEditPopup {

    private List<String> returnedDataList;
    private List<String> infoNameList;
    private List<String> infoValueList;
    private HashMap<Label, TextField> labelWithTextF;

    private Stage stage;
    private Scene scene;

    private VBox vBox;

    private HBox editHBox;
    private VBox leftVBox;
    private VBox rightVBox;

    private HBox buttonHBox;
    private Button confirmButton;
    private Button cancelButton;


    public List<String> display() {
        init();
        setButtonAction();
        stage.showAndWait();
        return returnedDataList;
    }
    public List<String> display(String title) {
        if(title == null){
            throw new NullPointerException();
        }
        init();
        setButtonAction();
        stage.setTitle(title);
        stage.showAndWait();
        return returnedDataList;
    }
    public void close(){

    }


    /**
     * 设置左侧的标签
     * @param infoName
     */
    public void setInfoNameList(String...infoName) {
        infoNameList = new ArrayList<>();
        infoNameList.addAll(Arrays.asList(infoName));
    }

    /**
     * 设置文本框内的文本，可以不设置，若设置需与左侧标签数目匹配
     * @param valueName
     */
    public void setInfoValueList(String...valueName){
        infoValueList = new ArrayList<>();
        infoValueList.addAll(Arrays.asList(valueName));
    }
    private void setButtonAction() {
        cancelButton.setOnAction(e -> {
            returnedDataList = null;
            stage.close();
        });
        confirmButton.setOnAction(e -> {
            returnedDataList = new ArrayList<>();
            for (Label key : labelWithTextF.keySet()) {
                String value = labelWithTextF.get(key).getText();
                if (value == null || value.length() == 0) {
                    PromptAlert.display("错误", "请不要留空");
                    return;
                }
                returnedDataList.add(value);
            }
            stage.close();
        });
    }
    /** 初始化布局元素 */
    private void init() {
        initEditField();//初始化编辑区域布局

        //初始化按钮布局
        cancelButton = new Button("返回");
        confirmButton = new Button("确定");

        buttonHBox = new HBox();
        buttonHBox.getChildren().addAll(cancelButton, confirmButton);
        buttonHBox.setSpacing(50);
        buttonHBox.setAlignment(Pos.CENTER);
        //初始化总布局
        vBox = new VBox();
        vBox.getChildren().addAll(editHBox, buttonHBox);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(10));

        scene = new Scene(vBox);
        stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    /** 初始化编辑区域布局 */
    private void initEditField() {
        int dataNumber = infoNameList.size();
        if (infoValueList != null){
            if(dataNumber != infoValueList.size())
                throw new IllegalArgumentException("标签列的长度与值列的长度不匹配");
        }
        labelWithTextF = new LinkedHashMap<>();
        leftVBox = new VBox();
        rightVBox = new VBox();
        for (int i = 0; i < dataNumber; i++) {
            Label label = new Label(infoNameList.get(i) + ":");
            TextField text = new TextField();
            if (infoValueList != null) text.setText(infoValueList.get(i));//如果未设置值列表，则不进行编辑框赋值
            labelWithTextF.put(label, text);

            leftVBox.getChildren().add(label);
            rightVBox.getChildren().add(text);
        }
        leftVBox.setSpacing(21);
        rightVBox.setSpacing(10);
        editHBox = new HBox();
        editHBox.getChildren().addAll(leftVBox, rightVBox);
        editHBox.setPadding(new Insets(10));
    }


}
