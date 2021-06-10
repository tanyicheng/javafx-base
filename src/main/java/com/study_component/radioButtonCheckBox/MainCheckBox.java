package com.study_component.radioButtonCheckBox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * CheckBox：多选框 事件与单选类似
 * @Author created by barrett in 2019/10/2 11:45
 */
public class MainCheckBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #eb6;");

        CheckBox c1 = new CheckBox("多选框1");
        CheckBox c2 = new CheckBox("多选框2");
        CheckBox c3 = new CheckBox("多选框3");
        CheckBox c4 = new CheckBox("多选框4");

        //默认选中
        c2.setSelected(true);
        //设置不确定状态，初始化显示一次
        c3.setIndeterminate(true);
        //允许有不确定状态
        c4.setAllowIndeterminate(true);


        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(c1,c2,c3,c4);


        anchorPane.getChildren().addAll(hbox);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();

        //多选框单独设置监听
        c1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println("c1单击事件："+newValue);
            }
        });

        //批量设置监听
        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                hbox.getChildren().forEach((item)->{
                    CheckBox cb = (CheckBox) item;
                    System.out.println(cb.getText()+" = "+cb.isSelected()+"不确定状态："+cb.isIndeterminate());

                });



            }
        });

    }
}
