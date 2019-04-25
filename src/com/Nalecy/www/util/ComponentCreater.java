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
    /**
     * 传入字符串，返回已设置好的Label对象
     * @param text 标签的文字
     * @return Label
     */
    public static Label newLabel(String text){
        return new Label(text);
    }
    /**
     * 传入字符串和宽度，返回已设置好的Label对象
     * @param text 标签文本
     * @param width 宽度
     * @return Label
     */
    public static Label newLabel(String text, int width){
        Label label = new Label(text);
        label.setMinWidth(width);
        label.setMaxWidth(width);
        return label;
    }
    /**
     * 传入字符串，返回已设置好的TextField对象
     * @param promptText 提示文本
     * @return TextField
     */
    public static TextField newTextField(String promptText){
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        return textField;
    }
    /**
     * 传入字符串和宽度，返回已设置好的TextField对象
     * @param promptText 提示文本
     * @param width 宽度
     * @return TextField
     */
    public static TextField newTextField(String promptText, int width){
        TextField textField = new TextField();
        textField.setMinWidth(width);
        textField.setMaxWidth(width);
        textField.setPromptText(promptText);
        return textField;
    }
    /**
     * 传入字符串和宽度，返回已设置好的PasswordField对象
     * @param promptText 提示文本
     * @return PasswordField
     */
    public static PasswordField newPasswordField(String promptText, int width){
        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(width);
        passwordField.setMaxWidth(width);
        passwordField.setPromptText(promptText);
        return passwordField;
    }
    /**
     * 传入字符串，返回已设置好的PasswordField对象
     * @param promptText 提示文本
     * @return PasswordField
     */
    public static PasswordField newPasswordField(String promptText){
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText(promptText);
        return passwordField;
    }

}
