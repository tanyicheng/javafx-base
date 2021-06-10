package com.study_component.layout.gridPane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * 网格布局
 * @Author created by barrett in 2019/9/28 13:25
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

        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #e6b43c");

        //TODO 设置组件在 GridPane 的第几列第几行
        gridPane.add(b1,0,0);
        gridPane.add(b2,1,0);
        gridPane.add(b3,2,0);
        gridPane.add(b4,3,0);
        gridPane.add(b5,0,1);
        gridPane.add(b6,1,1);
        gridPane.add(b7,2,1);

        //水平间距
        gridPane.setHgap(10);
        //垂直间距
        gridPane.setVgap(10);
        //单独给一个组件设置外边距
//        gridPane.setMargin(b1,new Insets(10));
        //设置居中
//        gridPane.setAlignment(Pos.CENTER);

        //TODO 单独设置b1主键位置，有了gridPane.add(b1)方法，不能再写getChildren().addAll(b1)
//        gridPane.setConstraints(b1,1,1);
//        gridPane.getChildren().addAll(b1);

        gridPane.getColumnConstraints().add(new ColumnConstraints((100)));//设置第一列间距
        gridPane.getRowConstraints().add(new RowConstraints((50)));//设置第一行间距

        Scene scene  = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
    }
}
