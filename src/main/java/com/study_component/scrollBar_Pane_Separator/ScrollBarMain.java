package com.study_component.scrollBar_Pane_Separator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 滚动条
 * @Author created by barrett in 2019/12/4 10:43
 */
public class ScrollBarMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        VBox box = new VBox();

        Button btn = new Button("点击滚动");
        box.setStyle("-fx-background-color: #ccc");
//        box.setLayoutX(100);
//        box.setLayoutY(100);

        ScrollBar bar = new ScrollBar();

        for(int i = 1; i <= 30;i++){
            box.getChildren().add(new Button("button"+i));
        }
        //布局方向
        bar.setOrientation(Orientation.VERTICAL);

        //滚动条可见数量
        bar.setVisibleAmount(50);
        //点击滚动条中间位置可移动量
        bar.setBlockIncrement(100);
        //每次滚动单位
        bar.setUnitIncrement(50);

        bar.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                box.setLayoutY(newValue.doubleValue());
            }
        });

        AnchorPane.setLeftAnchor(bar,100.0);
        AnchorPane.setLeftAnchor(btn,300.0);

        an.getChildren().addAll(box,bar,btn);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //设置box的高度
        bar.setPrefHeight(box.getHeight());
        bar.setMax(box.getHeight());
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

//                bar.decrement();//递减
                bar.increment();//递增
            }
        });


    }
}
