package com.Nalecy.www;

import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.LoginMenu;
import com.Nalecy.www.view.LoginView;
import com.Nalecy.www.view.Menu;
import javafx.application.Application;
import javafx.stage.Stage;

public class Runner extends Application {
   /* private static Runner ourInstance = new Runner();

    public static Runner getInstance() {
        return ourInstance;
    }

    private Runner() {
    }*/

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage a) {
        ViewManger.getInstance().switchView(null,new LoginView());
        // LoginView.getInstance().display();
        //showMenu(new LoginMenu());
    }
    private void showMenu(Menu m){
        m.show();
    }
}
