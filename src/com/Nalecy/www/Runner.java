package com.Nalecy.www;

import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage a) {
        ViewManger.switchView(new LoginView());
    }
}
