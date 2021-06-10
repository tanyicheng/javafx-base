package com.study_component.group;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.Mnemonic;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * 设置快捷键
 * @Author created by barrett in 2019/9/24 22:35
 */
public class MainKey extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        final Button b1 = new Button();
        b1.setText("按钮1");
        //设置文字颜色
//        b1.setTextFill(Paint.valueOf("#fff"));
        //设置位置
        b1.setLayoutX(100);
        b1.setLayoutY(100);
        //设置宽高
        b1.setPrefWidth(200);
        b1.setPrefHeight(100);

        //按钮单击监听
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("触发按钮");
                play();
            }
        });

        //容器
        Group group = new Group();
        group.getChildren().add(b1);

        Scene scene = new Scene(group);


        //设置快捷键
        //方式一： ctrl+alt+C
        KeyCombination k1 = new KeyCodeCombination(KeyCode.C,KeyCombination.ALT_DOWN,KeyCombination.CONTROL_DOWN);
        Mnemonic mnemonic = new Mnemonic(b1,k1);
        scene.addMnemonic(mnemonic);

        //TODO 方式二：推荐使用，各个平台兼容
        KeyCombination k2 = new KeyCodeCombination(KeyCode.Y,KeyCombination.SHORTCUT_DOWN);
        scene.getAccelerators().put(k2, new Runnable() {
            //这里并没有新开启一条线程
            @Override
            public void run() {
                System.out.println("run（）调用");

                //开火，调用b1按钮的事件，不建议这么做，推荐做法把b1中的内容封装成函数来调用
                //b1.fire();
                play();
            }
        });
        //方式三：
        KeyCombination kc3 = KeyCombination.valueOf("alt+k");
        Mnemonic mnemonic3 = new Mnemonic(b1,kc3);
        scene.addMnemonic(mnemonic3);

        primaryStage.setScene(scene);
        primaryStage.setTitle("javaFX");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }

    public void play(){
        System.out.println("封装业务逻辑");
    }
}
