package com.study_component.platform;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * 工具类
 * @Author created by barrett in 2019/9/22 21:54
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        System.out.println("start："+Thread.currentThread().getName());

        //TODO 在空闲时更新组件，但是如果大量频繁更新会导致线程阻塞
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                System.out.println("更新组件："+Thread.currentThread().getName());
            }
        });
        System.out.println("runLater 下面");

        //关闭窗口时不会退出
//        Platform.setImplicitExit(false);

        //笔记本是否支持一些功能
        System.out.println("屏幕是否支持3D："+ Platform.isSupported(ConditionalFeature.SCENE3D));
        System.out.println("是否支持FXML："+Platform.isSupported(ConditionalFeature.FXML));

        primaryStage.show();
        //完全关闭
//        Platform.exit();
    }
}
