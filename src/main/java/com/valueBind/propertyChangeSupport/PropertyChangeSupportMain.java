package com.valueBind.propertyChangeSupport;

import com.common.Student;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 对象中的值发生改变可以发出通知，配合监听器进行更改
 * @Author created by barrett in 2019/12/12 20:53
 */
public class PropertyChangeSupportMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Student s = new Student("张三",18);

        Button btn = new Button("点击修改");

        an.getChildren().addAll(btn);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //监听器（所有属性改变了都监听）
        s.psc.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                System.out.println("old  "+evt.getOldValue());
                System.out.println("new  "+evt.getNewValue());
            }
        });

        //指定更新的属性
        s.psc.addPropertyChangeListener("SetNamePro", new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

                System.out.println("指定属性old  "+evt.getOldValue());
                System.out.println("指定属性new  "+evt.getNewValue());
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                s.setName("李四");
                s.setAge(22);
            }
        });
    }
}
