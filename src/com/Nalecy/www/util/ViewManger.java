package com.Nalecy.www.util;

import com.Nalecy.www.view.View;

public class ViewManger {
    private static ViewManger instance;
    private ViewManger(){}
    public static ViewManger getInstance(){
        if(instance == null){
            instance = new ViewManger();
        }
        return instance;
    }
    public void switchView(View oldView,View newView){
        if(oldView != null)oldView.close();
        if(newView != null)newView.display();
    }
}
