package com.study_component.ColorPicker_DatePicker;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * 颜色选择器
 * @Author created by barrett in 2019/11/7 22:23
 */
public class ColorPickerMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane an = new AnchorPane();

        ColorPicker cp = new ColorPicker();

        cp.valueProperty().addListener(new ChangeListener<Color>() {
            @Override
            public void changed(ObservableValue<? extends Color> observable, Color oldValue, Color newValue) {

                System.out.println("监听事件  RGB："+newValue.getRed()+","+newValue.getGreen()+","+newValue.getBlue());

                //动态给根节点设置背景色 0x01f831ff,ff表示透明度
                String value = newValue.toString().substring(2);
                System.out.println("背景色: "+value);
                an.setStyle("-fx-background-color: #"+value);
            }
        });

        an.getChildren().addAll(cp);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
