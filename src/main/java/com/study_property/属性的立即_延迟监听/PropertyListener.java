package com.study_property.属性的立即_延迟监听;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.stage.Stage;

/**
 * 立即计算
 * 延迟计算
 * @Author created by barrett in 2020/1/23 09:14
 */
public class PropertyListener extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        SimpleIntegerProperty sip = new SimpleIntegerProperty(1);


        //立即计算
//        sip.addListener(new ChangeListener<Number>() {
//            @Override
//            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//                System.out.println("立即计算："+newValue);
//            }
//        });

        //延迟计算,前提不能调用ChangeListener，
        // fixme 可能会造成内存泄漏
//        sip.addListener(new InvalidationListener() {
//            @Override
//            public void invalidated(Observable observable) {
//                System.out.println("延迟计算"+observable);
//            }
//        });
        Inval inval = new Inval();
        sip.addListener(inval);

        sip.set(2);
        sip.get();//每次调用get时才会做计算，否则不会计算

        sip.set(3);
        sip.get();

        //在不想使用监听器的时候可以取消监听器，减少性能损耗
        sip.removeListener(inval);

        sip.set(4);
        sip.get();

        sip.set(5);

        //不显示窗体
        Platform.exit();
    }
}

//优化监听器可能导致的内存泄漏
class Inval implements InvalidationListener{

    @Override
    public void invalidated(Observable observable) {
        System.out.println("优化内存泄漏-延迟监听"+observable);
    }
}