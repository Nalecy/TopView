package com.Nalecy.www.util;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * 用于创建多行的提示性文本，以及对某行的文本进行增删改
 */
public class LabelsCreater {
    private VBox vBox;
    private Integer size;
    public LabelsCreater(){
        vBox = new VBox();
        vBox.setSpacing(10);
        size = 0;
    }
    public VBox getVBox(){
        return vBox;
    }
    public void addLine(String labelString){
        vBox.getChildren().add(new Label(labelString));
        size++;
    }

    /**
     * 设置下标为index的行的文本
     * @param index 下标(从0开始计数)
     * @param labelString 修改后的文本
     */
    public void setLine(Integer index,String labelString){
        Label label= (Label) vBox.getChildren().get(index);
        label.setText(labelString);
    }

    /**
     * 删除下标为index的行
     * @param index 下标(从0开始计数)
     */
    public void deleteLine(Integer index){
        vBox.getChildren().remove(index.intValue());
        size--;
    }

    /**
     * 获取行数
     * @return Integer
     */
    public Integer getSize() {
        return size;
    }
}
