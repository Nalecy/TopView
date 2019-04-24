package com.Nalecy.www.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


/**
 * 用于创建一个表
 * @param <T> 表元素的类
 */
public class TableViewCreater<T> {
    private TableView<T> tableView;
    public TableViewCreater(){
        tableView = new TableView<>();
    }

    /**
     * 为表格增加一列
     * @param text 列名
     * @param valueName 在类的属性中的名字
     * @param width 宽度
     */
    public void addColumn(String text,String valueName,Integer width){
        TableColumn<T,String> column = new TableColumn<>(text);
        column.setMaxWidth(width);
        column.setMinWidth(width);
        column.setCellValueFactory(new PropertyValueFactory<>(valueName));
        tableView.getColumns().add(column);
    }

    /**
     * 获取构建好的表格实例
     * @return TableView<T>
     */
    public TableView<T> getTableView(){
        return tableView;
    }




}
