package com.study_component.stage;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.stage.Stage;

/**
 * stage 窗口 模式、模态
 * @Author created by barrett in 2019/9/22 20:54
 */
public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        //设置背景透明度
        primaryStage.setOpacity(0.85);
        //设置在最顶层
        primaryStage.setAlwaysOnTop(true);
        //默认打开位置
        primaryStage.setX(100);
        primaryStage.setY(200);

        //计算左上角在屏幕中的位置
        primaryStage.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("窗口X坐标："+newValue);
            }
        });
        primaryStage.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("窗口y坐标："+newValue);
            }
        });

        primaryStage.show();
    }
}
