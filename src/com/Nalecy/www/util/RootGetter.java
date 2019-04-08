package com.Nalecy.www.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.net.URL;

public class RootGetter {
    static URL url;
    static Parent root;

    public static Parent getFxmlRoot(String fileName){
        StringBuilder sb = new StringBuilder();
        sb.append("/com/Nalecy/www/res/fxml/").append(fileName);
        try {
            url = RootGetter.class.getResource(sb.toString());
            root = FXMLLoader.load(url);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(-1);
        }
        return root;
    }
}
