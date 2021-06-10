package com.study_component.scrollBar_Pane_Separator;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 分割线条
 * @Author created by barrett in 2019/12/4 11:00
 */
public class SeparatorMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox box = new HBox();

        Button btn1 = new Button("button1");
        Button btn2 = new Button("button2");

        //垂直的
        Separator sep = new Separator(Orientation.VERTICAL);

        sep.setPrefHeight(200);
        sep.setPrefWidth(200);

        //设置分割线的位置,默认是bottom
        sep.setValignment(VPos.CENTER);

        box.getChildren().addAll(btn1,sep,btn2);
        an.getChildren().addAll(box);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}
