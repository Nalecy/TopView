package com.Nalecy.www.view.customerSubView;

import com.Nalecy.www.view.View;
import com.Nalecy.www.view.popupUtil.InfoEditPopup;
import javafx.stage.Stage;

public class PsnlInfoView extends View {
    private InfoEditPopup psnlInfoStage = new InfoEditPopup();

    @Override
    public void display(){
        setInfo();
    }

    private void setInfo() {
    }

    private void init(){
        InfoEditPopup psnlInfoStage = new InfoEditPopup();
        psnlInfoStage.setInfoValueList("");
    }

    @Override
    public void close() {
       // stage.close();
    }
}
