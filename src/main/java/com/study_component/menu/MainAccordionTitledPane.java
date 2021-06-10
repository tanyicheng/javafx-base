package com.study_component.menu;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.NodeOrientation;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Accordion
 * TitledPane 折叠
 * @Author created by barrett in 2019/10/1 13:10
 */
public class MainAccordionTitledPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #ccc");

        //TODO 手风琴折叠方式
        Accordion accordion = new Accordion();

        //TODO 折叠布局
        TitledPane titledPane = new TitledPane("折叠布局1",new Button("button1"));
        //动画效果
        titledPane.setAnimated(false);
        //是否默认展开
        titledPane.setExpanded(false);

        //监听
        titledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("titledPane 展开状态："+newValue);
            }
        });

        //手风琴折叠监听 TODO 在关闭时 newValue 是空值，直接调用会报异常
        accordion.expandedPaneProperty().addListener(new ChangeListener<TitledPane>() {
            @Override
            public void changed(ObservableValue<? extends TitledPane> observable, TitledPane oldValue, TitledPane newValue) {
                if(newValue == null){
                    System.out.println("关闭："+oldValue.getText());
                    return;
                }else{
                    System.out.println("展开："+newValue.getText());
                }
            }
        });

        TitledPane titledPane2 = new TitledPane();
        titledPane2.setText("TitlePane2");

        HBox box = new HBox();
        box.getChildren().addAll(new Button("button1"),new Button("button2"),new Button("button3"));
        box.setStyle("-fx-background-color: blue");

        titledPane2.setContent(box);
        //一般设置图标
        titledPane2.setGraphic(new Button("btn"));
        //从左到右
        titledPane2.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);


        AnchorPane.setTopAnchor(titledPane2,100.0);
        accordion.getPanes().addAll(titledPane,titledPane2);
        anchorPane.getChildren().addAll(accordion);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        primaryStage.show();
    }
}
