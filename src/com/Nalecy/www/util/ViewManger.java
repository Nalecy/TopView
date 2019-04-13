package com.Nalecy.www.util;

import com.Nalecy.www.view.View;

public class ViewManger {
    public static void switchView(View oldView,View newView){
        if(oldView != null)oldView.close();
        if(newView != null)newView.display();
    }
}
