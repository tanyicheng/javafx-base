package com.study_component.stage;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * 窗体
 * @Author created by barrett in 2019/9/22 19:32
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //窗体标题
        primaryStage.setTitle("JavaFx");

        //修改左上角图标
        primaryStage.getIcons().add(new Image("/icon/apple.png"));

        //设置最小化
//        primaryStage.setIconified(true);
        //设置最大化
//        primaryStage.setMaximized(true);
        //设置全屏，与最大化不一样，必须设置 Scene
//        primaryStage.setFullScreen(true);
        //简单来说，设置桌布才能摆放物品
        primaryStage.setScene(new Scene(new Group()));

        //显示窗体
        primaryStage.show();
        //自定义高度宽度
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        //设置窗口大小改变
        primaryStage.setResizable(true);

        //设置最大值，最小值
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(700);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(300);


        //获取窗体宽高，如果没有设置默认的宽高，这里需要放在show()方法后面才能获取到
        System.out.println("宽度："+primaryStage.getWidth());
        System.out.println("高度："+primaryStage.getHeight());

        //动态获取窗体宽高
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("当前高度："+newValue.doubleValue());
            }
        });
        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("当前宽度："+newValue.doubleValue());
            }
        });
        //关闭窗口
//        primaryStage.close();
    }
}
