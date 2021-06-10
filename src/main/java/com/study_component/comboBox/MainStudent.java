package com.study_component.comboBox;

import com.common.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * ComboBox 下拉菜单，item位置固定
 * 失去焦点时会刷新下拉菜单
 * 带有可编辑功能
 * 如果是操作对象用法参考 choiceBox
 * setConverter防止异常，此类可以指定占位符 cbb.setPromptText("请输入");
 *
 * @Author created by barrett in 2019/10/7 10:20
 */
public class MainStudent extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();

        Student stu1 = new Student("张三", 18, 89.0);
        Student stu2 = new Student("李四", 19, 67.0);
        Student stu3 = new Student("王五", 20, 78.0);
        Student stu4 = new Student("二愣子", 21, 98.0);
        Student stu5 = new Student("张嘎", 22, 90.0);

        ComboBox cbb = new ComboBox();
        ComboBox cbb2 = new ComboBox();
        cbb.getItems().addAll(stu1, stu2, stu3, stu4, stu5);

        //允许编辑
        cbb.setEditable(true);
        //文本提示，在没有焦点的时候出现
        cbb.setPromptText("请输入");
        //在item没有元素时用作提示
//        cbb.setPlaceholder(new Text("没加载到数据"));

        //显示滚动条
//        cbb.setVisibleRowCount(3);

        //转换器
        cbb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if (object != null) {
                    String val = object.getName() + " - " + object.getAge() + " - " + object.getScore();
                    //todo 更新到列表中
                    if (cbb.getItems().contains(object) == false) {
                        cbb.getItems().add(object);
                        System.out.println("更新列表内容--------------");
                    }
                    return val;
                }
                return null;
            }

            /**
             * fixme 这里有问题，在回车后会调用2次，导致添加2条数据，第二条数据有问题
             * @Author created by barrett in 2019/10/7 17:00
             */
            @Override
            public Student fromString(String string) {
                System.out.println("可编辑时回车或失去焦点后这里被调用：" + string);

                if (string != null && string != "") {
                    //将输入的内容添加到下拉菜单中
                    Student s = new Student(string, 17, 88);
                    return s;
                }
                return null;
            }
        });

        //选择列时的监听
        cbb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                System.out.println(newValue);
            }
        });

        cbb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("setOnAction");
            }
        });


        hBox.getChildren().addAll(cbb, cbb2);
        an.getChildren().addAll(hBox);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}
