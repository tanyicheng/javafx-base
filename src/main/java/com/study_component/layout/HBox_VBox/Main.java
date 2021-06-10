package com.study_component.layout.HBox_VBox;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 水平布局 HBox
 * 垂直布局 VBox
 * @Author created by barrett in 2019/9/26 08:37
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //布局类，基本操作与Group差不多
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #c2e6d8");


        Button b1 = new Button("按钮1");
        Button b2 = new Button("按钮2");
        Button b3 = new Button("按钮3");

        //水平布局
        HBox box = new HBox();
        box.setPrefHeight(200);
        box.setPrefWidth(400);
        box.setStyle("-fx-background-color: #cc8725");
        //内边距
        box.setPadding(new Insets(10,10,10,10));
        //外边距
        box.setMargin(b1,new Insets(10));
        //TODO 设置对齐方式：如果是中间对齐的方式，不管是否有内外边距都会居中，只有左右的边距会保留
        box.setAlignment(Pos.BOTTOM_CENTER);
        //设置组件之间的间距
        box.setSpacing(10);


        box.getChildren().addAll(b1,b2,b3);

        anchorPane.getChildren().addAll(box);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFx");
        primaryStage.setWidth(800);
        primaryStage.setHeight(500);


        primaryStage.show();
    }
}
