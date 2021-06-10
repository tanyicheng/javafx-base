package com.study_component.layout.anchorPane;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * AnchorPane 定位布局；锚点，
 * 子组件根据父组件的位置来定位的
 * @Author created by barrett in 2019/9/25 21:40
 */
public class MainAnchorPane extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //布局类，基本操作与Group差不多
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #c2e6d8");

        //设置鼠标单击事件
        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(123);
            }
        });

        Button b1 = new Button("按钮1");
        b1.setLayoutX(10);
        b1.setLayoutY(50);
        Button b2 = new Button("按钮2");
        Button b3 = new Button("按钮3");
        Button b4 = new Button("按钮4");

        /*//内边距：设置anchorPane组件的内边距，前提需要设置 setTopAnchor 才能看到效果
        anchorPane.setPadding(new Insets(10, 10, 10, 10));
        //设置bi按钮距离 anchorPane 顶部50像素，不是整个窗体的顶部
        //也可理解为 b1距离父控件 anchorPane 顶部50像素
        AnchorPane.setTopAnchor(b1, 50.0);
        AnchorPane.setLeftAnchor(b1, 50.0);*/


        //group容器放入按钮
        Group g1 = new Group();
        g1.getChildren().addAll(b1, b2);
        Group g2 = new Group();
        g2.getChildren().addAll(b3, b4);

        //布局类中放入group组件
        anchorPane.getChildren().addAll(g1, g2);
        AnchorPane.setTopAnchor(g1, 100.0);
        AnchorPane.setLeftAnchor(g1, 100.0);
        AnchorPane.setTopAnchor(g2, 100.0);
        AnchorPane.setLeftAnchor(g2, 300.0);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFx");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);


        primaryStage.show();
    }
}
