package com.study_component.pagination;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * 分页效果,非表格的分页
 * @Author created by barrett in 2019/11/13 21:53
 */
public class PaginationMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Pagination pa = new Pagination();
        pa.setStyle("-fx-background-color: #ccc");
        pa.setPrefHeight(200.0);
        pa.setPrefWidth(200.0);
        //分页按钮样式
//        pa.getStyleClass().add(Pagination.STYLE_CLASS_BULLET);//圆点式

        //位置
        pa.setLayoutY(100.0);
        pa.setLayoutX(100.0);
        //总页数 = 10
        pa.setPageCount(10);//Pagination.INDETERMINATE 总页数未知的
        //显示多少个按钮
        pa.setMaxPageIndicatorCount(6);
        //当前页 （这里是下标）
        pa.setCurrentPageIndex(3);


        //设置数据
        pa.setPageFactory(new Callback<Integer, Node>() {
            @Override
            public Node call(Integer param) {//页码下标
                HBox box  = new HBox();
                if(param == 0){
                    box.setStyle("-fx-background-color: #17C2FF");
                    box.getChildren().addAll(new Button("box-btn"));
                    return box;
                }else{
                    box.setStyle("-fx-background-color: #17C2"+param*10);
                    return box;
                }
//                return new Label(String.valueOf(param));
            }
        });



        an.getChildren().addAll(pa);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();



        //监听点击事件
        pa.currentPageIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //这里的newvalue也是下标
//                System.out.println("页数："+newValue+1);
            }
        });
    }
}
