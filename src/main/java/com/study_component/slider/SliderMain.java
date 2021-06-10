package com.study_component.slider;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 可拖动的进度条：类似音量调节功能
 * @Author created by barrett in 2019/11/23 20:25
 */
public class SliderMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Slider slider = new Slider(0,100,0);
        slider.setPrefWidth(400);
        slider.setShowTickMarks(true);//显示刻度
        slider.setShowTickLabels(true);//显示数字
        slider.setMajorTickUnit(10);//设置间距
        slider.setValue(20);

        //设置垂直或水平
//        slider.setOrientation(Orientation.VERTICAL);

        //todo 转换默认显示的数字
//        slider.setLabelFormatter(new StringConverter<Double>() {
//            @Override
//            public String toString(Double object) {
//                if(object.doubleValue() == 0){
//                    return "零";
//                }else if(object.doubleValue() == 10){
//                    return "壹";
//                }
//                return "un";
//            }
//
//            @Override
//            public Double fromString(String string) {
//                return null;
//            }
//        });

        //设置单击事件
        slider.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(event);
            }
        });

        an.getChildren().addAll(slider);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        //监听器
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println(newValue);
                //改变后主动发起通知，下面的方法有时会失效
                slider.setValueChanging(true);
            }
        });

        //值改变后通知
        slider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                System.out.println(newValue);
            }
        });
    }
}
