package com.study_component.application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 启动方式和生命周期
 * init() -- JavaFX-Launcher 初始化
 * start() -- JavaFX Application Thread UI组件主要业务逻辑
 * stop() -- JavaFX Application Thread
 * main() -- main
 * @Author created by barrett in 2019/9/22 18:58
 */
public class Main extends Application {
    public static void main(String[] args) {
        //启动方式1
        launch(args);
        //启动方式2,2类分开写
//        launch(LaunchMian.class,args);

        //程序不在同一个线程中运行
        System.out.println("main() -- "+Thread.currentThread().getName());
        Platform.exit();
    }


    @Override
    public void init() throws Exception {
        System.out.println("init() -- "+Thread.currentThread().getName());
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start() -- "+Thread.currentThread().getName());
        //显示窗口
        primaryStage.show();
    }


    @Override
    public void stop() throws Exception {
        //在窗口被关闭后调用
        System.out.println("stop() -- "+Thread.currentThread().getName());
    }
}
