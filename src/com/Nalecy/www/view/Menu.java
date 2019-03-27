package com.Nalecy.www.view;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner in = new Scanner(System.in);
    public abstract void show();
    public void showNextMenu(Menu menu) {
        menu.show();
    }
}
