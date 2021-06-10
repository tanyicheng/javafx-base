package com.study_component.menu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * CustomMenuItem：自定义菜单
 * MenuButton：下拉菜单按钮
 * SplitMenuButton：拆分的下拉菜单按钮
 *
 *
 * @Author created by barrett in 2019/9/30 22:33
 */
public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #ccc");
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Edit");
        Menu menu3 = new Menu("View");
        Menu menu4 = new Menu("Navigte");


        MenuItem item1 = new MenuItem("New");
        MenuItem item2 = new MenuItem("Open...");
        MenuItem item3 = new MenuItem("Open URL...");

        //Todo 自定义组件
        CustomMenuItem customItem = new CustomMenuItem();
        Button button = new Button("Button");
        customItem.setContent(button);

        CustomMenuItem customItem2 = new CustomMenuItem();
        //进度条
        ProgressBar bar = new ProgressBar(0.3);
        customItem2.setContent(bar);

        CustomMenuItem customItem3 = new CustomMenuItem();
        HBox hBox = new HBox();
        hBox.setPrefHeight(50);
        hBox.setStyle("-fx-background-color: red");
        hBox.getChildren().addAll(new Button("b1"),new Button("b3"),new Button("b2"));
        customItem3.setContent(hBox);

        // TODO 有分隔的下拉菜单按钮
        SplitMenuButton smb = new SplitMenuButton();
        smb.setText("SplitMenuButton");
        AnchorPane.setTopAnchor(smb,50.0);
        AnchorPane.setLeftAnchor(smb,200.0);

        //TODO 菜单下拉按钮
        MenuButton mb = new MenuButton("menuButtin");
        AnchorPane.setTopAnchor(mb,50.0);

        MenuItem item4 = new MenuItem("item4");
        MenuItem item5 = new MenuItem("item5");
        MenuItem item6 = new MenuItem("item6");
        MenuItem item7 = new MenuItem("item7");
        MenuItem item8 = new MenuItem("item8");
        MenuItem item9 = new MenuItem("item9");

        //设置快捷键
        item4.setAccelerator(KeyCombination.valueOf("ctrl+i"));

        item4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("item4单击事件");
            }
        });

        //TODO 针对设置的快捷键被调用时触发
        item4.setOnMenuValidation(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("item4.setOnMenuValidation");
            }
        });

        mb.getItems().addAll(item4,item5,item6);
        smb.getItems().addAll(item7,item8,item9);
        //添加菜单
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);
        menu1.getItems().addAll(item1, item2, item3,customItem,customItem2,customItem3);

        anchorPane.getChildren().addAll(menuBar,mb,smb);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

    }
}
