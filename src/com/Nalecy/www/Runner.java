package com.Nalecy.www;

import com.Nalecy.www.view.LoginMenu;
import com.Nalecy.www.view.Menu;

public class Runner {
    private static Runner ourInstance = new Runner();

    public static Runner getInstance() {
        return ourInstance;
    }

    private Runner() {
    }

    public static void main(String[] args) {
        Runner.getInstance().start();
    }

    private void start() {
        showMenu(new LoginMenu());
    }
    private void showMenu(Menu m){
        m.show();
    }
}
