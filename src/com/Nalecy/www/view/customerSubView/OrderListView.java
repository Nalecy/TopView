package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.view.alert.PromptAlert;

public class OrderListView {
    private static OrderListView instance;
    private OrderListView(){}
    public static OrderListView getInstance(){
        if(instance == null){
            instance = new OrderListView();
        }
        return instance;
    }
    public void display(){

    }
}
