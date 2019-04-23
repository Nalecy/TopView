package com.Nalecy.www.util;

import javafx.scene.control.Button;

public class ButtonCreater {
    /**
     * 传入一个字符串，返回以该字符串为名字的Button对象
     * @param buttonTitle 按钮的文字
     * @return Button
     */
    public static Button getNewButton(String buttonTitle){
        return new Button(buttonTitle);
    }

    /**
     * 传入字符串和宽度，返回已设置好的Button对象
     * @param buttonTitle 按钮的文字
     * @param width 宽度
     * @return Button
     */
    public static Button getNewButton(String buttonTitle,Integer width){
        Button button = new Button(buttonTitle);
        button.setMinWidth(width);
        button.setMaxWidth(width);
        return button;
    }
}
