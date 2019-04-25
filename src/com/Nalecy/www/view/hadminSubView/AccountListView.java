package com.Nalecy.www.view.hadminSubView;

import com.Nalecy.www.po.Account;
import com.Nalecy.www.po.forTableView.AccountT;
import com.Nalecy.www.service.BalanceService;
import com.Nalecy.www.util.ComponentCreater;
import com.Nalecy.www.util.ServiceFactory;
import com.Nalecy.www.util.TableViewCreater;
import com.Nalecy.www.util.ViewManger;
import com.Nalecy.www.view.View;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AccountListView extends View {
    private BalanceService balanceService = ServiceFactory.getBalanceService();
    private Stage stage;
    private Scene scene;
    private VBox vBox;

    private Label tipLabel;
    private TableView<AccountT> accountTable;
    private Button backButton;

    @Override
    public void display() {
        if(!hasInit){
            init();
            setButtonAction();
            hasInit = true;
        }
        stage.show();
    }

    private void setButtonAction() {
        backButton.setOnAction(e->{
            ViewManger.back();
        });
    }

    private void init(){
        tipLabel = new Label("下面是账单列表");

        TableViewCreater<AccountT> tvc = new TableViewCreater<>();
        tvc.addColumn("用户名","customerUserName",100);
        tvc.addColumn("账单日期","date",100);
        tvc.addColumn("交易金额","balance",100);
        tvc.addColumn("房间名字","roomName",100);
        tvc.addColumn("房间时段","roomPeriodString",100);
        accountTable = tvc.getTableView();
        accountTable.setItems(getAccountList());

        backButton = ComponentCreater.newButton("返回");

        vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(50));
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tipLabel,accountTable,backButton);

        scene = new Scene(vBox);
        stage = new Stage();
        stage.setTitle("查看账单");
        stage.setScene(scene);
    }

    private ObservableList<AccountT> getAccountList() {
        ObservableList<AccountT> accounts = FXCollections.observableArrayList();
        List<Account> accountList = balanceService.getAccountList();
        if(accountList != null){
            for (Account account : accountList) {
                accounts.add(new AccountT(account));
            }
        }
        return accounts;
    }

    @Override
    public void close() {
        stage.close();
    }

    @Override
    public void hide() {
        stage.hide();
    }
}
