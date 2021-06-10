package com.study_component.layout.borderPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 方位布局 BorderPane
 * @Author created by barrett in 2019/9/26 13:04
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button btn1 = new Button("btn1");

        //定点布局
        AnchorPane top = new AnchorPane();
        top.setPrefHeight(100);
        top.setPrefWidth(100);//TODO 虽然设置的宽度，但是由于左右没有组件会自动填充
        top.setStyle("-fx-background-color: #15ccb9");

        AnchorPane left = new AnchorPane();
        left.setPrefHeight(100);
        left.setPrefWidth(100);
        left.setStyle("-fx-background-color: #55cc0d");

        AnchorPane rigth = new AnchorPane();
        rigth.setPrefHeight(100);
        rigth.setPrefWidth(100);
        rigth.setStyle("-fx-background-color: #cc1416");

        AnchorPane bottom = new AnchorPane();
        bottom.setPrefHeight(100);
        bottom.setPrefWidth(100);
        bottom.setStyle("-fx-background-color: #166dcc");

        AnchorPane center = new AnchorPane();
        center.setPrefHeight(100);
        center.setPrefWidth(100);
        center.setStyle("-fx-background-color: #fbff02");

        //方位布局
        BorderPane bp = new BorderPane();
        bp.setStyle("-fx-background-color: #969696");

        //设置内边距
        bp.setPadding(new Insets(20));
        //设置顶部组件的下外边距
        bp.setMargin(top,new Insets(0,0,10,0));

        //设置对齐方式
        bp.setAlignment(btn1, Pos.CENTER);

        bp.setTop(btn1);
//        bp.setTop(top); //放这个效果看不出
        bp.setRight(rigth);
        bp.setBottom(bottom);
        bp.setLeft(left);
        //TODO 如果上下左右不设置宽高，则只会显示中间这个颜色的组件，因为会自动填充，
        //TODO 然而现在看到的中间的组件虽然都设置了宽高，但是由于BorderPane的配置策略，会将其拉伸填充
        bp.setCenter(center);


        Scene scene = new Scene(bp);

        primaryStage.setScene(scene);
        primaryStage.setHeight(700);
        primaryStage.setWidth(700);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
    }
}
