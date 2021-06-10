package com.study_component.screen;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;


public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        //打开某个网页（在默认浏览器中）,下面的代码照常运行
        HostServices host = getHostServices();
        host.showDocument("www.baidu.com");

        Button button = new Button("按钮");
        button.setCursor(Cursor.CLOSED_HAND);//设置鼠标样 式
        //可以理解为布局、组件、容器
        Group group = new Group();
        //布局中放入按钮
        group.getChildren().add(button);

        //场景中放入layout 布局，
        Scene scene = new Scene(group);
//        scene.setCursor(Cursor.HAND);
        //也可以自定义
        URL url = getClass().getClassLoader().getResource("icon/apple.png");
        String path = url.toExternalForm();
        scene.setCursor(Cursor.cursor(path));
        //窗口中放入场景
        primaryStage.setScene(scene);

        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
    }
}
