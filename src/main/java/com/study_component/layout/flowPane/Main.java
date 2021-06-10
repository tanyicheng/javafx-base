package com.study_component.layout.flowPane;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * 流式布局：在窗口缩小时，组件会随之改变位置，HBox 则是压缩组件
 * @Author created by barrett in 2019/9/26 16:47
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 = new Button("Barrett1");
        Button b2 = new Button("Barrett2");
        Button b3 = new Button("Barrett3");
        Button b4 = new Button("Barrett4");
        Button b5 = new Button("Barrett5");
        Button b6 = new Button("Barrett6");
        Button b7 = new Button("Barrett7");

        FlowPane fp = new FlowPane();
        fp.setStyle("-fx-background-color: #ccc");
        //TODO 这里只设置b1按钮外边距，但是所有的flowPane 都会在一个水平线上，缩小窗体后垂直布局会有差异
//        fp.setMargin(b1,new Insets(10));
        //设置居中
        fp.setAlignment(Pos.CENTER);
        //设置水平间距
        fp.setHgap(10);
        //垂直间距
        fp.setVgap(10);
        //布局方向，垂直
        fp.setOrientation(Orientation.VERTICAL);

        fp.getChildren().addAll(b1,b2,b3,b4,b5,b6,b7);

        Scene scene = new Scene(fp);
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);

        primaryStage.show();
    }
}
