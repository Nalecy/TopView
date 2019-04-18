package com.Nalecy.www.view;

public abstract class View {
    protected boolean hasInit = false;
    public abstract void display();
    public abstract void close();
    public abstract void hide();
}
