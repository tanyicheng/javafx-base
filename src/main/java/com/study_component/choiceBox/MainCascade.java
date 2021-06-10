package com.study_component.choiceBox;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 级联选择（类似 省市区）
 *
 * @Author created by barrett in 2019/10/6 15:56
 */
public class MainCascade extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //数据源
        ObservableList<String> list1 = FXCollections.observableArrayList();
        list1.addAll("数字", "字母", "中文");
        //子集合
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("1", "2", "3", "4", "5", "6");
        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3.addAll("a", "b", "c", "d");
        ObservableList<String> list4 = FXCollections.observableArrayList();
        list4.addAll("云纹","云南","长沙","北京","上海");

        Button btn = new Button("排序");

        AnchorPane an = new AnchorPane();
        HBox box = new HBox();
        ChoiceBox<String> cb = new ChoiceBox<String>();
        ChoiceBox<String> cb2 = new ChoiceBox<String>();

        cb.setPrefWidth(100);
        //添加元素
        cb.getItems().addAll(list1);
        //默认选中
//        cb.setValue("item2");

        box.getChildren().addAll(cb, cb2, btn);
        an.getChildren().addAll(box);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //根据父类选择的带出子集合
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("数字")) {
                    cb2.setItems(list2);
                } else if ("字母".equals(newValue)) {
                    cb2.setItems(list3);
                }else if ("中文".equals(newValue)) {
                    cb2.setItems(list4);
                }
                //直接显示
                cb2.show();
            }
        });

        //排序
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //倒序
               /* list2.sort(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        int n = Integer.valueOf(o1);
                        int k = Integer.valueOf(o2);
                        return k-n;
                    }
                });*/
               //第二种排序,可以来回排序
                FXCollections.reverse(list2);
            }
        });

    }
}

