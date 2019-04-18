package com.Nalecy.www.util;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

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
    }
    public void setLine(Integer index,String labelString){
        Label label= (Label) vBox.getChildren().get(index);
        label.setText(labelString);
    }
    public void deleteLine(Integer index){
        vBox.getChildren().remove(index.intValue());
    }

    public Integer getSize() {
        return size;
    }
}
