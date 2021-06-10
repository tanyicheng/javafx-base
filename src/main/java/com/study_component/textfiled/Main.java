package com.study_component.textfiled;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.UnaryOperator;

/**
 * TextField
 * 输入格式限制
 *
 * @Author created by barrett in 2019/10/5 09:04
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);

        //设置标签
        Label label = new Label("账号：");
        label.setLayoutX(40);
        label.setLayoutY(100);

        Label pLabel = new Label("密码：");
        pLabel.setLayoutX(40);
        pLabel.setLayoutY(150);

        //密码文本框
        PasswordField passwordField = new PasswordField();
        //定位
        passwordField.setLayoutX(100);
        passwordField.setLayoutY(150);

        passwordField.setPromptText("密码");

        final TextField text = new TextField();
        final TextField text2 = new TextField();
//        text.setText("这里是文本");
        TextArea area = new TextArea();
        area.setPrefHeight(50);
        //定位
        text.setLayoutX(100);
        text.setLayoutY(100);
        text2.setLayoutX(100);
        text2.setLayoutY(200);
        area.setLayoutX(100);
        area.setLayoutY(250);

        //宽高
        text.setPrefWidth(200);
        text.setPrefHeight(30);

        text.setFont(Font.font(14));
        //设置样式
        //text.setStyle("");
        //鼠标放上去提示
        Tooltip tooltip = new Tooltip("我是提示");
        tooltip.setFont(Font.font(30));
        text.setTooltip(tooltip);

        //灰色文本提示
        text.setPromptText("请输入不超过7个字符");
        //监听输入
        text.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.length() > 7) {
                    //不能超过7个字符，这里也可以限制用户输入非法字符等
                    text.setText(oldValue);
                }
            }
        });
        //监听选中的值
        text.selectedTextProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("选中了：" + newValue);
            }
        });

        //单击文本事件方法一：监听鼠标点击事件
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(11);
            }
        });

        //单击文本事件方法二： 这是给文本监听鼠标发生的事件
        text.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(33);
            }
        });

        //todo 输入格式校验（每个字符）
        text2.setTextFormatter(new TextFormatter<Object>(new UnaryOperator<TextFormatter.Change>() {
            @Override
            public TextFormatter.Change apply(TextFormatter.Change change) {
                System.out.println("输入的内容：" + change.getText());
                String val = change.getText();
                //如果输入的是0-9之间的数字允许返回，否则不允许
                if (val.matches("[0-9]*")) {
                    return change;
                }
                return null;
            }
        }));

        //todo 网上资料，未研究
        DecimalFormat format = new DecimalFormat( "#.0" );
        TextArea field = new TextArea();
        field.setLayoutY(300);
        field.setTextFormatter(new TextFormatter<>(c ->
        {
            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Object object = format.parse(c.getControlNewText(), parsePosition);

            if (object == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                return c;
            }
        }));


        //值传递流程1-2-3 fixme 启动报空指针
//        area.setTextFormatter(new TextFormatter<String>(new StringConverter<String>() {
//            //2、fromString（）返回值进入这里
//            @Override
//            public String toString(String object) {
//                System.out.println("obj = "+object);
//                //3、这里的值返回给TextArea UI控件
//                return null;
//            }
//
//            //1、用户输入的值进入此方法
//            @Override
//            public String fromString(String string) {
//                System.out.println("str = "+string);
//                return null;
//            }
//        }));
        //需要提交值
//        area.commitValue();

        //文本框放入容器中
        group.getChildren().addAll(text, text2, area,field, passwordField, label, pLabel);

        primaryStage.show();
    }
}
