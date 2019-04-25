package com.Nalecy.www.util;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ComponentCreater {
    private ComponentCreater(){
        throw new AssertionError("请勿实例化ComponentCreater");
    }
    /**
     * 传入一个字符串，返回以该字符串为名字的Button对象
     * @param buttonTitle 按钮的文字
     * @return Button
     */
    public static Button newButton(String buttonTitle){
        return new Button(buttonTitle);
    }

    /**
     * 传入字符串和宽度，返回已设置好的Button对象
     * @param buttonTitle 按钮的文字
     * @param width 宽度
     * @return Button
     */
    public static Button newButton(String buttonTitle, int width){
        Button button = new Button(buttonTitle);
        button.setMinWidth(width);
        button.setMaxWidth(width);
        return button;
    }
    public static Label newLabel(String text){
        return new Label(text);
    }

    public static Label newLabel(String text, int width){
        Label label = new Label(text);
        label.setMinWidth(width);
        label.setMaxWidth(width);
        return label;
    }

    public static TextField newTextField(int width){
        TextField textField = new TextField();
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        return textField;
    }
    public static TextField newTextField(String promptText, int width){
        TextField textField = new TextField();
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setPromptText(promptText);
        return textField;
    }
    public static PasswordField newPasswordField(String promptText, int width){
        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(width);
        passwordField.setMaxWidth(width);
        passwordField.setPromptText(promptText);
        return passwordField;
    }


}
