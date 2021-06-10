package com.study_component.stage;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 窗口模式
 * @Author created by barrett in 2019/9/22 21:28
 */
public class Main3 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage s1 = new Stage();
        s1.setTitle("S1");
//        s1.initStyle(StageStyle.DECORATED);//默认的模式
//        s1.initStyle(StageStyle.TRANSPARENT);//透明的
//        s1.initStyle(StageStyle.UNDECORATED);//白色背景，不带装饰，跟TRANSPARENT效果一样
//        s1.initStyle(StageStyle.UNIFIED);//窗体标题不带颜色
//        s1.initStyle(StageStyle.UTILITY);//窗体标题栏只有关闭按钮
        s1.show();

        Stage s2 = new Stage();
        s2.setTitle("S2");

        s2.show();

//        Stage s3 = new Stage();
//        s3.setTitle("S3");
//        s3.show();
//
//        Stage s4 = new Stage();
//        s4.setTitle("S4");
//        s4.show();

        //关闭所有窗口
//        Platform.exit();
    }
}
