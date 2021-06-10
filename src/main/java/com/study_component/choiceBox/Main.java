package com.study_component.choiceBox;

import com.common.Student;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * ChoiceBox 下拉框 建议数据量不多的情况采用,下拉内容的位置会上下改变
 * TODO 修改ChoiceBox列表选项的问题
 * @Author created by barrett in 2019/10/6 14:15
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //被选中的学生对象
    Student student = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        VBox box = new VBox();
        ChoiceBox<String> cb = new ChoiceBox<String>();
        cb.setPrefWidth(100);
        //添加元素
        cb.getItems().addAll("item1","item2","item3");

        //默认选中的2种方式
        cb.setValue("item2");
//        cb.getSelectionModel().select("item3");

        TextField field = new TextField();
        Button btn = new Button("修改");

        //TODO 下拉选项是对象
        Student stu1= new Student("张三",18,89.0);
        Student stu2= new Student("李四",19,67.0);
        Student stu3= new Student("王五",20,78.0);
        Student stu4= new Student("二愣子",21,98.0);
        Student stu5= new Student("张嘎",22,90.0);
        ChoiceBox<Student> cb2 = new ChoiceBox<Student>();
        cb2.getItems().addAll(stu1,stu2,stu3,stu4,stu5);

        //TODO 转换器，数据从 toString(Student object) 进，return 出，注意在转换中需要判断对象是否为空
        cb2.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                String val = object.getName()+" - "+object.getAge()+" - "+object.getScore();
                System.out.println("列表刷新");
                return val;
            }

            @Override
            public Student fromString(String string) {
                System.out.println("这里并没有被调用");
                return null;
            }
        });

        box.getChildren().addAll(cb,btn,field,cb2);
        an.getChildren().addAll(box);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //监听事件
        cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                System.out.println(newValue);
            }
        });

        cb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Student>() {
            @Override
            public void changed(ObservableValue observable, Student oldValue, Student newValue) {
                student = newValue;
                System.out.println(newValue.getName()+"  "+newValue.getAge());
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = field.getText();
                if(student != null){
                    student.setName(text);
                    System.out.println(text);
                    //获取选中对象在列表中的位置
                    int index = cb2.getItems().indexOf(student);
                    //移除原来的对象
                    cb2.getItems().remove(student);
                    //新对象加入到原来的位置
                    cb2.getItems().add(index, student);
                }else{
                    System.out.println("没有选中值");
                }
            }
        });
    }
}

