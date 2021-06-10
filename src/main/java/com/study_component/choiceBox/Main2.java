package com.study_component.choiceBox;

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
 * 改良版，修改ChoiceBox列表选项
 * @Author created by barrett in 2019/10/6 14:15
 */
public class Main2 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    //被选中的学生对象
    StudentSimple student = new StudentSimple();

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        VBox box = new VBox();

        TextField field = new TextField();
        Button btn = new Button("修改");

        //TODO 下拉选项是对象
        StudentSimple stu1= new StudentSimple("张三",18,89.0);
        StudentSimple stu2= new StudentSimple("李四",19,67.0);
        StudentSimple stu3= new StudentSimple("王五",20,78.0);
        StudentSimple stu4= new StudentSimple("二愣子",21,98.0);
        StudentSimple stu5= new StudentSimple("张嘎",22,90.0);
        ChoiceBox<StudentSimple> cb2 = new ChoiceBox<StudentSimple>();
        cb2.getItems().addAll(stu1,stu2,stu3,stu4,stu5);

        //转换器，数据从 toString(StudentSimple object) 进，return 出
        cb2.setConverter(new StringConverter<StudentSimple>() {
            @Override
            public String toString(StudentSimple object) {
                String val = object.getName()+" - "+object.getAge()+" - "+object.getScore();
                System.out.println("列表刷新");
                return val;
            }

            @Override
            public StudentSimple fromString(String string) {
                System.out.println("这里并没有被调用");
                return null;
            }
        });

        box.getChildren().addAll(btn,field,cb2);
        an.getChildren().addAll(box);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //监听事件
        cb2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StudentSimple>() {
            @Override
            public void changed(ObservableValue observable, StudentSimple oldValue, StudentSimple newValue) {
                student = newValue;
                System.out.println("当前选中的值："+newValue.getName()+"  "+newValue.getAge());
                //TODO 新赋值的对象需要立即添加一个监听，如果这里执行以后再点击修改不会重新加载下拉菜单
                student.nameProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        System.out.println("修改内容 = "+newValue);

                        //获取选中对象在列表中的位置
                        int index = cb2.getItems().indexOf(student);
                        //移除原来的对象
                        cb2.getItems().remove(student);
                        //新对象加入到原来的位置
                        cb2.getItems().add(index, student);
                    }
                });
            }
        });

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String text = field.getText();
                if(student != null){
                    student.setName(text);
                    System.out.println("点击修改  "+text);

                }else{
                    System.out.println("没有选中值");
                }

            }
        });
    }
}

