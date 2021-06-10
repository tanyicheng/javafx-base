package com.study_property.list_set_map;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

/**
 * list集合的操作
 * @Author created by barrett in 2020/1/20 20:40
 */
public class ListProperty extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        ObservableList<String> list = FXCollections.observableArrayList();

        list.add("A");
        list.add("B");
        list.add("C");

        SimpleListProperty<String> slp = new SimpleListProperty<String>(list);

        //监听方式一
//        slp.addListener(new ChangeListener<ObservableList<String>>() {
//            @Override
//            public void changed(ObservableValue<? extends ObservableList<String>> observable, ObservableList<String> oldValue, ObservableList<String> newValue) {
//
//                newValue.forEach(System.out::println);
//            }
//        });

        //监听方式二
        slp.addListener(new ListChangeListener<String>() {
            @Override
            public void onChanged(Change<? extends String> c) {
                System.out.println(c);
                c.getList().forEach(item-> System.out.println(item));

                //TODO 将索引指定到第一次更改的那个位置上
                while (c.next()){
                    System.out.println("起始位置："+c.getFrom());
                    System.out.println("结束位置："+c.getTo());
                    System.out.println("是否添加的："+c.wasAdded());
                    System.out.println("是否移除的："+c.wasRemoved());
                    System.out.println("是否替换的："+c.wasReplaced());
                    System.out.println("是否重排序："+c.wasPermutated());
                    System.out.println("是否更新的："+c.wasUpdated());

                    System.out.println("添加的元素集合："+c.getAddedSubList());
                }
            }
        });

        list.addAll("G","F","D");

        //排序,最好直接在list做排序，用slp排序执行过程比list 多，
        list.sort(String::compareTo);


        //不显示窗体
        Platform.exit();
    }
}
