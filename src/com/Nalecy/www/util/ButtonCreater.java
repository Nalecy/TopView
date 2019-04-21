package com.Nalecy.www.util;

import javafx.scene.control.Button;

public class ButtonCreater {
    public static Button getNewButton(String buttonTitle){
        return new Button(buttonTitle);
    }
    public static Button getNewButton(String buttonTitle,Integer width){
        Button button = new Button(buttonTitle);
        button.setMinWidth(width);
        button.setMaxWidth(width);
        return button;
    }
}
