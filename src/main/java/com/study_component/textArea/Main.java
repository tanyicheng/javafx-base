package com.study_component.textArea;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        an.setStyle("-fx-background-color: #ccc");

        TextArea area = new TextArea();
        area.setText("设置文本");
        //字体大小
        area.setFont(Font.font(16));
        //是否自动换行
        area.setWrapText(false);
        //宽高
        area.setPrefWidth(400);
        area.setPrefHeight(200);

        //行数和列数
//        area.setPrefRowCount(2);
//        area.setPrefColumnCount(2);

        area.appendText("<追加字符>");
        //删除字符
        area.deleteText(0,2);
        //插入字符
//        area.insertText(3,"<插入字符>");

//        area.replaceText(0,1,"替换字符");

        //选中所有
//        area.selectAll();
        //选中首个字符
//        area.selectForward();
        //获取选中文字到第几个字符
//        area.selectPositionCaret(5);
        //选择区域
        area.selectRange(2,5);
        //光标位置
//        area.positionCaret(3);
        //是否可编辑
//        area.setEditable(false);
        //清除:不推荐，一般使用 setText("")
//        area.clear();


        an.getChildren().addAll(area);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();

        //点击文本框触发事件
        area.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //点击只会移动到20个像素的位置，不是每次移动20像素
                area.setScrollLeft(20);
            }
        });
        //限制文本输入
        /*area.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.length() > 10){
                    //不能超过7个字符，这里也可以限制用户输入非法字符等
                    area.setText(oldValue);
                }
            }
        });*/
        //选择时监听
        area.selectedTextProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });
        //滚动监听
        area.scrollLeftProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("左右滚动监听："+newValue);
            }
        });
    }
}
