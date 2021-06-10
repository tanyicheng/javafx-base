package com.study_component.splitPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 分割可拖拽的面板
 * @Author created by barrett in 2019/11/27 18:52
 */
public class SplitPaneMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button b1 = new Button("btn1");
        Button b2 = new Button("btn2");
        Button b3 = new Button("btn3");
        Button b4 = new Button("btn4");


        SplitPane sp = new SplitPane();
        sp.setPrefWidth(700);
//        sp.setPrefHeight(400);
        //设置水平还是垂直
//        sp.setOrientation(Orientation.VERTICAL);//垂直，注意需要给sp设置高度

        //面板
        StackPane s1 = new StackPane();
        //设置最小宽度
        s1.setMinWidth(100);
        s1.getChildren().addAll(b1);

        StackPane s2 = new StackPane();
        s2.getChildren().addAll(b2);

        StackPane s3 = new StackPane();
        s3.getChildren().addAll(b3);

        StackPane s4 = new StackPane();
        s4.getChildren().addAll(b4);

        //设置初始平分宽度,index 是元素下标，position 位置
        sp.setDividerPosition(0,0.25);
        sp.setDividerPosition(1,0.5);
//        sp.setDividerPosition(2,0.75);
//        sp.setDividerPosition(3,1);


        sp.getItems().addAll(s1,s2);

        an.getChildren().addAll(sp);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
