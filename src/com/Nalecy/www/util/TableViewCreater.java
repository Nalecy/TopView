package com.Nalecy.www.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;
import java.util.LinkedList;

public class TableViewCreater<T> {
    private TableView<T> tableView;
    public TableViewCreater(){
        tableView = new TableView<>();
    }
    public void addStringColumn(String text,String valueName,Integer width){
        TableColumn<T,String> column = new TableColumn<>(text);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(valueName));
        tableView.getColumns().add(column);
    }
    public void addIntegerColumn(String text,String valueName,Integer width){
        TableColumn<T,Integer> column = new TableColumn<>(text);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(valueName));
        tableView.getColumns().add(column);
    }
    public void addDoubleColumn(String text,String valueName,Integer width){
        TableColumn<T,Double> column = new TableColumn<>(text);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(valueName));
        tableView.getColumns().add(column);
    }
    public void addDateColumn(String text,String valueName,Integer width){
        TableColumn<T, Date> column = new TableColumn<>(text);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(valueName));
        tableView.getColumns().add(column);
    }
    public TableView<T> getTableView(){
        return tableView;
    }




}
