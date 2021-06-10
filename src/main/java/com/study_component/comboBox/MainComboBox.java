package com.study_component.comboBox;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * 1、自定义单元格：解析 setCellFactory()
 * @Author created by barrett in 2019/10/21 21:51
 */
public class MainComboBox extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //数据源
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll("数字", "字母", "中文");

        AnchorPane an = new AnchorPane();
        HBox box = new HBox();
        ComboBox<String> cb = new ComboBox<String>();
        cb.setPrefWidth(100);
        //添加元素
        cb.getItems().addAll(list1);

        //对象转换
        cb.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String object) {
//                System.out.println(object);
                return object;
            }

            @Override
            public String fromString(String string) {
                return null;
            }
        });

        cb.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                MyListCell<String> list = new MyListCell<String>();

//                list.updateItem(param);
//                param.getItems().forEach(item -> {
//                    System.out.println("param："+item);
//                });
                return list;
            }
        });

        box.getChildren().addAll(cb);
        an.getChildren().addAll(box);
        AnchorPane.setTopAnchor(box,100.0);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}


class MyListCell<T> extends ListCell<String> {
    @Override
    protected void updateItem(String item, boolean empty) {

        super.updateItem(item, empty);

        System.out.println("item: "+item+";  empty: "+empty);

        if(empty == false){
            HBox box = new HBox(10);
            box.setAlignment(Pos.CENTER);//此容器内居中
            box.setStyle("-fx-background-color: #1bcc88");
            box.getChildren().addAll(new Button(item), new Button(item+"2"));

            this.setStyle("-fx-background-color: #cc25a0");
            this.setAlignment(Pos.CENTER);
            //修改位置为右侧
            this.setContentDisplay(ContentDisplay.CENTER);
            //设置此属性可以当容器；不要设置文本
            this.setGraphic(box);
        }

    }
}
