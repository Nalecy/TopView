package com.Nalecy.www.util;

import com.Nalecy.www.view.View;

import java.util.LinkedList;

public class ViewManger {
    private ViewManger (){
        viewStack = new LinkedList<>();
    }
    private static ViewManger instance = new ViewManger();
    public static ViewManger getInstance(){
        return instance;
    }

    private static LinkedList<View> viewStack;

    public static void switchView(View newView){
        if(viewStack.size() != 0){
            viewStack.getFirst().hide();
        }
        if(newView != null){
            newView.display();
            viewStack.push(newView);
        }
    }
    public static void back(){
        viewStack.pop().close();
        viewStack.getFirst().display();
    }
}
