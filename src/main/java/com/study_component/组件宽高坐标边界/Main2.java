package com.study_component.组件宽高坐标边界;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 边界例子
 *
 * @Author created by barrett in 2019/12/5 21:10
 */
public class Main2 extends Application {

    double x = 0;
    double y = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button b1 = new Button("button1");
        Button b2 = new Button("button2");
        b2.setEffect(new DropShadow());//设置样式
        b2.setRotate(40);//倾斜度

        AnchorPane.setTopAnchor(b2, 100.0);
        AnchorPane.setLeftAnchor(b2, 100.0);


        an.getChildren().addAll(b2, b1);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //todo
        System.out.println("不带任何样式的边界："+b2.getLayoutBounds());
        System.out.println("带有样式、效果的边界："+b2.getBoundsInLocal());
        System.out.println(""+b2.getBoundsInParent());

        Bounds bod2 = b2.getLayoutBounds();
        Point2D p1 = b2.localToParent(bod2.getMinX(), bod2.getMinY());
        Point2D p2 = b2.localToParent(bod2.getMaxX(), bod2.getMaxY());

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Bounds bod1 = b1.getLayoutBounds();

            Point2D p3 = null;
            Point2D p4 = null;

            @Override
            public void handle(KeyEvent event) {

                if (event.getCode().getName().equals(KeyCode.A.getName())) {
                    x -= 10.0;
                    b1.setLayoutX(x);
//                    getLocalToParent(p3, p4, bod1, b1);
                    p3 = b1.localToParent(bod1.getMinX(), bod1.getMinY());
                    p4 = b1.localToParent(bod1.getMaxX(), bod1.getMaxY());
                } else if (event.getCode().getName().equals(KeyCode.W.getName())) {
                    y -= 10.0;
                    b1.setLayoutY(y);
//                    getLocalToParent(p3, p4, bod1, b1);
                    p3 = b1.localToParent(bod1.getMinX(), bod1.getMinY());
                    p4 = b1.localToParent(bod1.getMaxX(), bod1.getMaxY());
                } else if (event.getCode().getName().equals(KeyCode.D.getName())) {
                    x += 10.0;
                    b1.setLayoutX(x);
//                    getLocalToParent(p3, p4, bod1, b1);

                    p3 = b1.localToParent(bod1.getMinX(), bod1.getMinY());
                    p4 = b1.localToParent(bod1.getMaxX(), bod1.getMaxY());
                } else if (event.getCode().getName().equals(KeyCode.S.getName())) {
                    y += 10.0;
                    b1.setLayoutY(y);
//                    getLocalToParent(p3, p4, bod1, b1);

                    p3 = b1.localToParent(bod1.getMinX(), bod1.getMinY());
                    p4 = b1.localToParent(bod1.getMaxX(), bod1.getMaxY());
                }

                //b1的右下角是否进入b2按钮内部
                if (p4 != null && p4.getX() >= p1.getX() && p4.getY() >= p1.getY()
                    && p4.getX() <= p2.getX() && p4.getY() <= p2.getY()) {

                    System.out.println("相撞"+p4);
                }else{
                    System.out.println("没有相撞"+p3);
                }
            }
        });

    }

    //这种方式无效
    public void getLocalToParent(Point2D p3, Point2D p4, Bounds bod1, Button b1) {
        p3 = b1.localToParent(bod1.getMinX(), bod1.getMinY());
        p4 = b1.localToParent(bod1.getMaxX(), bod1.getMaxY());

    }
}
