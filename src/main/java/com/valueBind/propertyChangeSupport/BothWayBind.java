package com.valueBind.propertyChangeSupport;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.awt.*;

/**
 * 双向绑定
 * @Author created by barrett in 2020/1/27 10:19
 */
public class BothWayBind extends Application {

    public static void main(String[] args) {
        launch(args);


        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(5);

        System.out.println("x: "+x.get()+"   y: "+y.get());

        //x绑定在y上面,x不能设置值，
        x.bindBidirectional(y);
//        x.set(3);
        y.set(4);

        System.out.println("x: "+x.get()+"   y: "+y.get());

        //解绑
        x.unbindBidirectional(y);
    }


    /**
     * 双向绑定例子：修改t1中的内容，t2跟随改变
     * @Author created by barrett in 2020/1/27 11:24
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();
//        hBox.setStyle("-fx-background-color: #ccc");
        hBox.setLayoutX(100);
        hBox.setLayoutY(100);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        t1.textProperty().bindBidirectional(t2.textProperty(), new StringConverter<String>() {
            @Override
            public String toString(String object) {
                return null;
            }

            @Override
            public String fromString(String string) {
                if(string.contains("5")){
                    string = string.replace("5","五");
                }
                return string;
            }
        });
        hBox.getChildren().addAll(t1,t2);

        an.getChildren().addAll(hBox);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
    }

}
