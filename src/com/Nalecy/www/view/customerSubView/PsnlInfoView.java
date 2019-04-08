package com.Nalecy.www.view.customerSubView;

public class PsnlInfoView {
    private static PsnlInfoView instance;
    private PsnlInfoView(){}
    public static PsnlInfoView getInstance(){
        if(instance == null){
            instance = new PsnlInfoView();
        }
        return instance;
    }
    public void display(){

    }
}
