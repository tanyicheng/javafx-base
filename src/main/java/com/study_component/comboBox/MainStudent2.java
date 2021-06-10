package com.study_component.comboBox;

import com.common.Student;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * 2、自定义单元格：解析 setCellFactory()
 * @Author created by barrett in 2019/10/27 19:47
 */
public class MainStudent2 extends Application {

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

        ComboBox<Student> cbb = new ComboBox<Student>();
        cbb.getItems().addAll(stu1,stu2,stu3,stu4,stu5);

        //转换器（todo 这里根据选中的值实现到select中）
        cbb.setConverter(new StringConverter<Student>() {
            @Override
            public String toString(Student object) {
                if(object != null){
                    String val = object.getName()+" - "+object.getAge()+" - "+object.getScore();
                    //todo 更新到列表中
                    if(cbb.getItems().contains(object) == false){
                        cbb.getItems().add(object);
                        System.out.println("更新列表内容--------------");
                    }
                    return val;
                }
                return null;
            }

            @Override
            public Student fromString(String string) {

                return null;
            }
        });

        //TODO 此方法不写，下拉列表也会显示，如果自定义了，就必须要设置Text
        cbb.setCellFactory(new Callback<ListView<Student>, ListCell<Student>>() {
            @Override
            public ListCell<Student> call(ListView<Student> param) {

                ListCell<Student> listCell = new ListCell<Student>(){

                    @Override
                    protected void updateItem(Student item, boolean empty) {
                        super.updateItem(item, empty);

                        //这里显示下拉列表中的选项，与选中的值无关
                        if(empty == false){
//                            String val = cbb.getConverter().toString(item);
                            ImageView img = new ImageView("icon/apple.png");
                            img.setFitHeight(20);
                            img.setFitWidth(20);

                            Label name = new Label(item.getName());
                            name.setStyle("-fx-background-color: #2619cc;");
                            Label age = new Label(String.valueOf(item.getAge()));
                            Label soure = new Label(String.valueOf(item.getScore()));

                            HBox box = new HBox(10);
                            box.setAlignment(Pos.CENTER);//居中
//                            box.setStyle("-fx-background-color: #ccc");
                            box.getChildren().addAll(img,name, age,soure);

                            //设置此属性可以当容器
                            this.setGraphic(box);
                        }
                    }
                };



//                list.updateItem(param);
//                param.getItems().forEach(item -> {
//                    System.out.println("param："+item);
//                });
                return listCell;
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
