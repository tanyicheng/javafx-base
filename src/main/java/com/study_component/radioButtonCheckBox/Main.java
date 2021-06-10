package com.study_component.radioButtonCheckBox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * RadioButton：单选框
 * @Author created by barrett in 2019/10/2 11:45
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #eb6;");

        RadioButton radio1= new RadioButton("单选一");
        RadioButton radio2= new RadioButton("单选二");
        RadioButton radio3= new RadioButton("单选三");
        RadioButton radio4= new RadioButton("单选四");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(radio1,radio2,radio3,radio4);

        //必须要用组，否则没有单选效果
        ToggleGroup tg = new ToggleGroup();
        radio1.setToggleGroup(tg);
        radio2.setToggleGroup(tg);
        radio3.setToggleGroup(tg);
        radio4.setToggleGroup(tg);
        //默认选中
//        radio2.setSelected(true);
        //默认选中
        tg.selectToggle(radio3);


        anchorPane.getChildren().addAll(hbox);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();

        //监听单选一是否选中
        radio1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
            }
        });

        //
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radio = (RadioButton) newValue;
                System.out.println("单选按钮："+radio.getText());
            }
        });
    }
}
