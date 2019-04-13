package com.Nalecy.www.discardMenu;

import java.util.Scanner;

public abstract class Menu {  //废弃
    protected Scanner in = new Scanner(System.in);
    public abstract void show();
    public void showNextMenu(Menu menu) {
        menu.show();
    }
}
