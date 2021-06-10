package com.study_component.menu;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * ContextMenu：右键菜单
 * @Author created by barrett in 2019/10/1 11:25
 */
public class MainContextMenu extends Application {

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

        Button button = new Button("右键事件");

        MenuItem item1 = new MenuItem("New");
        MenuItem item2 = new MenuItem("Open...");
        MenuItem item3 = new MenuItem("Open URL...");

        //TODO 右键菜单
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(item1,item2,item3);
        button.setContextMenu(contextMenu);
        AnchorPane.setTopAnchor(button,50.0);

        //右键单击事件
        button.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {
            @Override
            public void handle(ContextMenuEvent event) {
                Button source = (Button) event.getSource();
                System.out.println("setOnContextMenuRequested:   "+source.getText());
            }
        });

        //添加菜单
        menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);
        menu1.getItems().addAll(item1, item2, item3);

        anchorPane.getChildren().addAll(menuBar,button);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
