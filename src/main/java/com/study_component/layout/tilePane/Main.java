package com.study_component.layout.tilePane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

/**
 * 特性：以当前组件最大块为准、对齐，
 * @Author created by barrett in 2019/9/28 15:04
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("Barrett1");
        Button b2 = new Button("Barrett2");
        //其他组件虽然不会改变高度，但是依旧会对齐
        b2.setPrefHeight(200);
        Button b3 = new Button("Barrett3");
        Button b4 = new Button("Barrett4");
        Button b5 = new Button("Barrett5");
        Button b6 = new Button("Barrett6");
        Button b7 = new Button("Barrett7");

        TilePane tilePane = new TilePane();
        tilePane.setStyle("-fx-background-color: #eb6");
        //给b2设置外边距，其他组件都会跟随
        tilePane.setMargin(b2,new Insets(50));
        //对齐方式
//        tilePane.setOrientation(Orientation.VERTICAL);

        tilePane.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7);
        Scene scene = new Scene(tilePane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
    }
}
