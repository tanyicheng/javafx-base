package com.study_component.comboBox;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * label 当容器使用
 * TODO 这里有个小坑，jdk8中AnchorPane添加ListCell<String>()会报反射异常，换成jdk9就没事了
 * Exception in Application start method
 * java.lang.reflect.InvocationTargetException
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * 	at java.lang.reflect.Method.invoke(Method.java:498)
 * 	at com.sun.javafx.application.LauncherImpl.launchApplicationWithArgs(LauncherImpl.java:389)
 * 	at com.sun.javafx.application.LauncherImpl.launchApplication(LauncherImpl.java:328)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
 * 	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
 * 	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
 * 	at java.lang.reflect.Method.invoke(Method.java:498)
 * 	at sun.launcher.LauncherHelper$FXHelper.main(LauncherHelper.java:767)
 * Caused by: java.lang.RuntimeException: Exception in Application start method
 * 	at com.sun.javafx.application.LauncherImpl.launchApplication1(LauncherImpl.java:917)
 * 	at com.sun.javafx.application.LauncherImpl.lambda$launchApplication$154(LauncherImpl.java:182)
 * 	at java.lang.Thread.run(Thread.java:748)
 * Caused by: java.lang.NullPointerException
 * 	at com.sun.javafx.scene.control.skin.ListCellSkin.<init>(ListCellSkin.java:42)
 * 	at javafx.scene.control.ListCell.createDefaultSkin(ListCell.java:338)
 * 	at javafx.scene.control.Control.impl_processCSS(Control.java:872)
 * 	at javafx.scene.Parent.impl_processCSS(Parent.java:1280)
 * 	at javafx.scene.Node.processCSS(Node.java:9056)
 * 	at javafx.scene.Scene.doCSSPass(Scene.java:545)
 * 	at javafx.scene.Scene.preferredSize(Scene.java:1643)
 * 	at javafx.scene.Scene.impl_preferredSize(Scene.java:1720)
 * 	at javafx.stage.Window$9.invalidated(Window.java:864)
 * 	at javafx.beans.property.BooleanPropertyBase.markInvalid(BooleanPropertyBase.java:109)
 * 	at javafx.beans.property.BooleanPropertyBase.set(BooleanPropertyBase.java:144)
 * 	at javafx.stage.Window.setShowing(Window.java:940)
 * 	at javafx.stage.Window.show(Window.java:955)
 * 	at javafx.stage.Stage.show(Stage.java:259)
 * 	at com.study.comboBox.MainTestLabel.start(MainTestLabel.java:77)
 * 	at com.sun.javafx.application.LauncherImpl.lambda$launchApplication1$161(LauncherImpl.java:863)
 * 	at com.sun.javafx.application.PlatformImpl.lambda$runAndWait$174(PlatformImpl.java:326)
 * 	at com.sun.javafx.application.PlatformImpl.lambda$null$172(PlatformImpl.java:295)
 * 	at java.security.AccessController.doPrivileged(Native Method)
 * 	at com.sun.javafx.application.PlatformImpl.lambda$runLater$173(PlatformImpl.java:294)
 * 	at com.sun.glass.ui.InvokeLaterDispatcher$Future.run(InvokeLaterDispatcher.java:95)
 * 	at com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
 * 	at com.sun.glass.ui.win.WinApplication.lambda$null$147(WinApplication.java:177)
 * 	... 1 more
 * Exception running application com.study.comboBox.MainTestLabel
 * @Author created by barrett in 2019/10/22 22:02
 */
public class MainTestLabel extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);//此容器内居中
        box.setMaxHeight(150);//设置普通宽高无效
        box.setMaxWidth(150);
        box.setStyle("-fx-background-color: #1bcc88");
        box.getChildren().addAll(new Button("btn1"), new Button("btn2"));

        Label la1 = new Label();
        la1.setStyle("-fx-background-color: #cc25a0");
        la1.setPrefHeight(200);
        la1.setPrefWidth(200);
        la1.setAlignment(Pos.CENTER);
        //修改位置为右侧
        la1.setContentDisplay(ContentDisplay.RIGHT);
        //设置此属性可以当容器；不要设置文本
        la1.setGraphic(box);
//        la1.setText("Label-1");


        HBox box2 = new HBox(10);
        box2.setAlignment(Pos.CENTER);//此容器内居中
        box2.setMaxHeight(150);//设置普通宽高无效
        box2.setMaxWidth(150);
        box2.setStyle("-fx-background-color: #1bcc88");
        box2.getChildren().addAll(new Button("btn1"), new Button("btn2"));

        ListCell<String> la2 = new ListCell<>();
        la2.setStyle("-fx-background-color: #cc25a0");
        la2.setPrefHeight(200);
        la2.setPrefWidth(200);
        la2.setAlignment(Pos.CENTER);
        la2.setContentDisplay(ContentDisplay.CENTER);
        la2.setGraphic(box2);

        MyListCellTest myListCell = new MyListCellTest();
        myListCell.updateItem("hello",true);

        AnchorPane.setTopAnchor(la1, 100.0);
        AnchorPane.setTopAnchor(myListCell, 300.0);

        an.getChildren().addAll(myListCell);

        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}

class MyListCellTest<T> extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);//此容器内居中
        box.setMaxHeight(150);//设置普通宽高无效
        box.setMaxWidth(150);
        box.setStyle("-fx-background-color: #1bcc88");
        box.getChildren().addAll(new Button(item), new Button(item+"2"));

        this.setStyle("-fx-background-color: #cc25a0");
        this.setPrefHeight(200);
        this.setPrefWidth(200);
        this.setAlignment(Pos.CENTER);
        //修改位置为右侧
        this.setContentDisplay(ContentDisplay.RIGHT);
        //设置此属性可以当容器；不要设置文本
        this.setGraphic(box);
    }
}

