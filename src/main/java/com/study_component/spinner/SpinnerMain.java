package com.study_component.spinner;

import com.common.Student;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * 下拉列表
 * 自定义叠加对象：https://www.bilibili.com/video/av36674882/?spm_id_from=trigger_reload
 * @Author created by barrett in 2019/11/27 19:08
 */
public class SpinnerMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();

        Spinner spinner = test();
//        Spinner spinner = test2();

        //设置可编辑
//        spinner.setEditable(true);

        Student s1 = new Student("A",16);
        Student s2 = new Student("B",17);
        Student s3 = new Student("C",18);

//        Spinner<Student> spinner = new Spinner<>();
//        StudentValueFactory svf = new StudentValueFactory();
//
//        svf.setConverter(new StringConverter<Student>() {
//            @Override
//            public String toString(Student object) {
//                return null;
//            }
//
//            @Override
//            public Student fromString(String string) {
//                return null;
//            }
//        });
//
//        spinner.setValueFactory(svf);


        an.getChildren().addAll(spinner);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        System.out.println(spinner.getValue());
    }

    public Spinner test() {
        //最小值，最大值，初始时，每次调整几个数字（默认是1）
        Spinner spinner = new Spinner<Integer>(0, 10, 5, 3);
        return  spinner;
    }


    //自定义元素
    public Spinner test2() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        obList.addAll("A", "B", "C", "D", "E");
        Spinner spinner = new Spinner(obList);
        return  spinner;
    }
}

class StudentValueFactory extends SpinnerValueFactory<Student>{

    //递减
    @Override
    public void decrement(int steps) {
        System.out.println("decrement："+steps);
    }

    //递增
    @Override
    public void increment(int steps) {

        System.out.println("increment：" +steps);
    }
}
