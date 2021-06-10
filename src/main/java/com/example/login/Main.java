package com.example.login;

import com.study_component.layout.gridPane.MyWindow;
import com.study_component.layout.gridPane.User;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 登录框
 * @Author created by barrett in 2019/9/29 20:11
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //和web的<a>标签类似
        Hyperlink hyperlink = new Hyperlink("www.baidu.com",new Button("点击打开"));
        hyperlink.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //从默认浏览器打开网址
                HostServices hostServices = Main.this.getHostServices();
                hostServices.showDocument(hyperlink.getText());
            }
        });

        Label lName = new Label("账号：");
        Label lPassword = new Label("密码：");

        //账号输入框
        TextField tName = new TextField();
        tName.setTooltip(new Tooltip("请输入账号"));
        //密码输入框
        PasswordField passwordField = new PasswordField();
        //鼠标移上去提示
        passwordField.setTooltip(new Tooltip("请输入密码"));
        //给组件设置用户数据，还可以设置map
        tName.setUserData(new User().getUserName());
        passwordField.setUserData(new User().getPassword());
        tName.getProperties().put("key","123");

        Button bLogin= new Button("登录");
        bLogin.setPrefWidth(100);
        Button bReset= new Button("重置");


        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #ffde8c");
        gridPane.setAlignment(Pos.CENTER);//居中

        gridPane.add(lName,0,0);
        gridPane.add(lPassword,0,1);
        gridPane.add(tName,1,0);
        gridPane.add(passwordField,1,1);
        gridPane.add(hyperlink,0,3);

        gridPane.add(bReset,0,2);
        gridPane.add(bLogin,1,2);
        //设置水平间距
        gridPane.setHgap(10);
        //垂直间距
        gridPane.setVgap(15);
        //设置外边距
        GridPane.setMargin(bLogin, new Insets(0,0,0,50));

        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("登录");
        primaryStage.setWidth(500);
        primaryStage.setHeight(300);
        //不允许缩放
        primaryStage.setResizable(false);
        primaryStage.show();

        //重置按钮监听事件
        bReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tName.setText("");
                passwordField.setText("");
            }
        });

        //登录按钮监听事件
        bLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //模拟数据库中的用户信息
                User user = new User();
                //获取页面的登录信息
                String name = tName.getText();
                String password = passwordField.getText();
                if(user.getUserName().equals(name) && user.getPassword().equals(password)){
                    System.out.println("登录成功");
                    //打开控制台窗口
                    new MyWindow(name,password);
                    //关闭登录窗口
                    primaryStage.close();
                }else{
                    System.out.println("登录失败，账号密码错误");
                    //设置label文字颜色
                    lName.setTextFill(Color.valueOf("#CC0400"));
                    lPassword.setTextFill(Color.RED);
                    //动画效果,呼吸灯
                    FadeTransition fadeTransition = new FadeTransition();
                    //动画时间，0.5秒
                    fadeTransition.setDuration(Duration.seconds(0.5));
                    fadeTransition.setNode(gridPane);//设置哪个节点
                    //0-1 透明度
                    fadeTransition.setFromValue(0);
                    fadeTransition.setToValue(1);

                    //执行
                    fadeTransition.play();
                }
            }
        });
    }
}
