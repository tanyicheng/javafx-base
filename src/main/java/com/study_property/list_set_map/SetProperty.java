package com.study_property.list_set_map;

import com.common.Student;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.*;
import javafx.stage.Stage;

import java.util.function.BiConsumer;

/**
 * set 、map集合，参考list的监听方法
 *
 * @Author created by barrett in 2020/1/20 20:41
 */
public class SetProperty extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {


        ObservableSet<String> set = FXCollections.observableSet("A", "B", "C");
        SimpleSetProperty ssp = new SimpleSetProperty(set);

        ssp.addListener(new SetChangeListener() {
            @Override
            public void onChanged(Change change) {
//                System.out.println(change);
            }
        });

        ssp.add("D");


        //Map
        ObservableMap<Object, Object> map = FXCollections.observableHashMap();

        map.put("a","1");
        map.put("b","2");
        SimpleMapProperty smp = new SimpleMapProperty(map);

        map.addListener(new MapChangeListener<Object, Object>() {
            @Override
            public void onChanged(Change<?, ?> change) {
                ObservableMap<?, ?> map1 = change.getMap();
                System.out.println("监听："+map1);
            }
        });

        map.forEach(new BiConsumer() {
            @Override
            public void accept(Object o, Object o2) {
                System.out.println(o+" - "+o2);
            }
        });

        map.put("c","2");

        //不显示窗体
        Platform.exit();
    }
}
