package com.study_component.组件宽高坐标边界;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 组件宽高、坐标、边界详解：https://www.bilibili.com/video/av37116709
 * @Author created by barrett in 2019/12/4 15:28
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();
        hBox.setStyle("-fx-background-color: #a6aacc");
        hBox.setPrefWidth(300);
        hBox.setPrefHeight(300);
        hBox.setLayoutX(100);
        hBox.setLayoutY(100);
        //居中
        hBox.setAlignment(Pos.CENTER);

        Button btn = new Button("button");
        Rectangle rec = new Rectangle();


        hBox.getChildren().addAll(btn,rec);

        an.getChildren().addAll(hBox);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        System.out.println("是否允许设置初始宽高："+btn.isResizable());
        System.out.println("是否允许设置初始宽高："+rec.isResizable());

        //获取坐标
        Bounds bounds = btn.getLayoutBounds();
        System.out.println("左上角x："+bounds.getMinX());
        System.out.println("左上角y："+bounds.getMinY());
        System.out.println("右下角x："+bounds.getMaxX());
        System.out.println("右下角y："+bounds.getMaxY());

        System.out.println("宽度："+bounds.getWidth());
        System.out.println("高度："+bounds.getHeight());

        Point2D p1 = btn.localToParent(bounds.getMinX(), bounds.getMinY());
        System.out.println("按钮在父组件HBox中的坐标："+p1.getX()+","+p1.getY());

        Point2D p2 = btn.localToScene(bounds.getMinX(), bounds.getMinY());
        System.out.println("按钮在Scene组件的坐标："+p2.getX()+","+p2.getY());

        Point2D p3 = btn.localToScreen(bounds.getMinX(), bounds.getMinY());
        System.out.println("按钮在屏幕中坐标："+p3.getX()+","+p3.getY());

    }
}
