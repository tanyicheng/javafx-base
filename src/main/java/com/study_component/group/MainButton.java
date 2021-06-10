package com.study_component.group;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;



/**
 * Button按钮以及简单介绍设置背景颜色和外边框等问题
 * @Author created by barrett in 2019/9/24 08:17
 */
public class MainButton extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 = new Button();
        b1.setText("按钮1");
        //设置文字颜色
//        b1.setTextFill(Paint.valueOf("#fff"));


        //设置位置
        b1.setLayoutX(100);
        b1.setLayoutY(100);
        //设置宽高
        b1.setPrefWidth(200);
        b1.setPrefHeight(100);
        //设置字体和大小
        b1.setFont(Font.font("sans-serif",30));
        //方式一：设置背景颜色,(颜色，圆角，边框和背景的间距：相当于margin,) TODO #00112200 ,后面2位代表透明度
//        BackgroundFill bgf = new BackgroundFill(Paint.valueOf("#001122"),new CornerRadii(10),new Insets(10));
//        Background bg = new Background(bgf);
//        b1.setBackground(bg);
//        //设置边框(样色，边框样式：实线虚线等，圆角，边框宽度)
//        BorderStroke bos = new BorderStroke(Paint.valueOf("#ccc"),BorderStrokeStyle.SOLID,new CornerRadii(5),new BorderWidths(5));
//        Border bo = new Border(bos);
//        b1.setBorder(bo);

        //方式二：设置样式,背景色，圆角
        b1.setStyle("-fx-background-color:#10cc1e;-fx-background-radius:10;-fx-text-fill:#fff;");

        //设置按钮单击事件
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Button btn = (Button) event.getSource();
                System.out.println("b1单击事件，按钮名："+btn.getText());
            }
        });
        //鼠标单击
        b1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MouseButton.PRIMARY.name();//常量
                System.out.println("鼠标按键："+event.getButton().name());
                //跟随系统设置的双击速度频率
                if(event.getClickCount() == 2){
                    System.out.println("鼠标双击");
                }
            }

        });

        //检测键盘按下
        b1.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                KeyCode.A.getName();//常量
                System.out.println("键盘按下："+event.getCode().getName());
            }
        });
        //释放
        b1.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("键盘释放："+event.getCode().getName());
            }
        });


        //容器
        Group group = new Group();
        group.getChildren().add(b1);

        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setTitle("javaFX");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
}
