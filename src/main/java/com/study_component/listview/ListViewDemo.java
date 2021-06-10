package com.study_component.listview;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 视频 https://www.bilibili.com/video/av42086423
 * @author created by barrett in 2021/5/25 14:15
 **/
public class ListViewDemo extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane an = new AnchorPane();
        VBox vbox = new VBox();
        ObservableList<String> list = FXCollections.observableArrayList();
        list.add("姓名：杨先生	年龄：18	性别：男");
        list.add("姓名：杨女生1	年龄：18	性别：女");
        list.add("姓名：杨女生2	年龄：18	性别：女");
        list.add("姓名：杨女生3	年龄：18	性别：女");
        list.add("姓名：杨女生4	年龄：18	性别：女");
        ListView<String> listview = new ListView();
        //占位符 当listview没有数据时显示占位符
        listview.setPlaceholder(new Label("没有数据"));
        //添加一个可观测的列表显示
        listview.setItems(list);

        Button b2 = new Button("删除选中数据");
        vbox.getChildren().addAll(b2);
        //设置可多选
        listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //设置默认选择项
        listview.getSelectionModel().select(0);

        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //获得ListView中选中数据的可视化集合
                ObservableList<String> list1 = listview.getSelectionModel().getSelectedItems();
                list.removeAll(list1);
            }
        });
        //单选事件	item的监听
        listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                System.out.println(newValue);
            }
        });
        //单选事件	索引的监听
        listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // TODO Auto-generated method stub
                System.out.println(newValue.intValue());
            }
        });
        //TODO:设置可编辑
        listview.setEditable(true);
        //直接对数据源进行修改
        listview.setCellFactory(TextFieldListCell.forListView());
        //设置单元格尺寸
        listview.setFixedCellSize(50);


        an.getChildren().addAll(listview, vbox);
        AnchorPane.setTopAnchor(listview, 50.0);
        AnchorPane.setLeftAnchor(listview, 30.0);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("javafx");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
}
