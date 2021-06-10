package com.study_component.comboBox;

import com.common.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.function.Predicate;

/**
 * ComboBox 下拉菜单,根据输入的过滤选项
 * @Author created by barrett in 2019/10/7 10:20
 */
public class MainSelect extends Application {

    //原始列表
    ObservableList<Student> obsList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();

        Student stu1= new Student("张三",18,89.0);
        Student stu2= new Student("李四",19,67.0);
        Student stu3= new Student("王五",20,78.0);
        Student stu4= new Student("二愣子",21,98.0);
        Student stu5= new Student("张嘎",22,90.0);

        ComboBox cbb = new ComboBox();
        cbb.getItems().addAll(stu1,stu2,stu3,stu4,stu5);

        //允许编辑
        cbb.setEditable(true);
        //文本提示，在没有焦点的时候出现
        cbb.setPromptText("请输入");
        //在item没有元素时用作提示
//        cbb.setPlaceholder(new Text("没加载到数据"));

        //显示
//        cbb.setVisibleRowCount(3);
        obsList = cbb.getItems();

        //转换器
        cbb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if(object != null){
                    String val = object.getName()+" - "+object.getAge()+" - "+object.getScore();

                    return val;
                }
                return null;
            }


            @Override
            public Student fromString(String string) {
                System.out.println("可编辑时回车或失去焦点后这里被调用："+string);

                return null;
            }
        });

        //获取输入的内容
        TextField field = (TextField) cbb.editorProperty().get();

        //给这个文本添加监听
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("newValue: "+newValue);
                if(newValue == null){
                    cbb.setItems(null);
                    cbb.setPlaceholder(new Label("没有匹配的信息"));
                    return;
                }

                //过滤器：返回一个新的集合
                FilteredList<Student> list = obsList.filtered(new Predicate<Student>() {

                    @Override
                    public boolean test(Student stu) {
//                        System.out.println("filter: "+stu.getName()+"         "+newValue);
                        //判断用户输入的内容是否与下拉菜单中的名字匹配
                        if(stu.getName().contains(newValue)){
                            return true;
                        }
                        return false;
                    }
                });

                System.out.println(list);
                if(list.isEmpty()){
                    cbb.setItems(null);
                    cbb.setPlaceholder(new Label("没有匹配的信息"));
                }else{
                    //如果匹配到了赋值，切显示下拉菜单
                    cbb.setItems(list);
                    cbb.show();
                }
            }
        });

        hBox.getChildren().addAll(cbb);
        an.getChildren().addAll(hBox);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}
