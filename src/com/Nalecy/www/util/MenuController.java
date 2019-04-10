package com.Nalecy.www.util;

import javafx.scene.control.MenuItem;

public class MenuController {
    private static MenuController instance;
    private MenuController(){}
    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }
        return instance;
    }


    public MenuItem back;
    public MenuItem outLoginMenu;
    public MenuItem exit;
    public MenuItem menuPsnlInfo;


}
