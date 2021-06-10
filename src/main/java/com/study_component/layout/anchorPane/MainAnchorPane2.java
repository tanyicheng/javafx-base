package com.study_component.layout.anchorPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * AnchorPane 定位布局；锚点，
 * 子组件根据父组件的位置来定位的
 *
 * @Author created by barrett in 2019/9/25 21:40
 */
public class MainAnchorPane2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //布局类，基本操作与Group差不多
        AnchorPane ap1 = new AnchorPane();
        AnchorPane ap2 = new AnchorPane();
        ap1.setStyle("-fx-background-color: #c2e6d8");
        ap2.setStyle("-fx-background-color: #e69509");


        Button b1 = new Button("按钮1");
        Button b2 = new Button("按钮2");
        Button b3 = new Button("按钮3");
        Button b4 = new Button("按钮4");

        //TODO b1脱离父级管控，且消失，不占位
//        b1.setManaged(false);
        //TODO 按钮消失，但是依然会占位
        b1.setVisible(true);
        //TODO 设置按钮透明度
        b1.setOpacity(0.8);

        ap2.setBottomAnchor(b1, 0.0);
        ap2.setLeftAnchor(b1, 0.0);


        ap2.getChildren().addAll(b1);
        //ap2是ap1的子组件
        ap1.getChildren().addAll(ap2);


        Scene scene = new Scene(ap1);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFx");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        primaryStage.show();

        //TODO 注意这里需要放在show方法后面，因为在没有显示窗体时，是无法获取宽高的
        ap1.setTopAnchor(ap2, 0.0);
        ap1.setBottomAnchor(ap2, ap1.getHeight() / 2);
        ap1.setLeftAnchor(ap2, ap1.getWidth() / 2);
        ap1.setRightAnchor(ap2, 0.0);


        //这里拖动窗体大小，ap2 的大小没有按比例缩放，原因是没有加监听实时处理,TODO 后期还有方法处理
        primaryStage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //TODO 注意这里不能用 newVauel,这里获取的高度会加上窗体顶部的标题栏，同理宽度也一样，会加上窗体的阴影部分
                ap1.setBottomAnchor(ap2, ap1.getHeight() / 2);
            }
        });

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                ap1.setLeftAnchor(ap2, ap1.getWidth() / 2);
            }
        });

    }
}
