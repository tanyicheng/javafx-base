package com.study_component.group;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * group: 布局、组件、容器
 * @Author created by barrett in 2019/9/23 21:55
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 = new Button("b1");
        Button b2 = new Button("b2");
        Button b3 = new Button("b3");

//        b2.setDisable(true);
        b2.setStyle("-fx-background-color: #e60000");

        b1.setLayoutX(0);
        b1.setLayoutY(10);
        b1.setPrefWidth(50);
        b1.setPrefHeight(50);

        b2.setLayoutX(100);
        b2.setLayoutY(10);

        b3.setLayoutX(200);
        b3.setLayoutY(10);

        final Group group = new Group();
//        group.getChildren().add(b1);
//        group.getChildren().add(b2);
//        group.getChildren().add(b3);
        //批量添加组件
        group.getChildren().addAll(b1,b2,b3);
        //移除组件,根据下标移除，clear 清除所有
//        group.getChildren().remove(0);
        //坐标只判断组件的左上角
        System.out.println("是否存在组件："+group.contains(0,10));

        //这是一个可监听组件的
        group.getChildren().addListener(new ListChangeListener<Node>() {
            @Override
            public void onChanged(Change<? extends Node> c) {
                System.out.println("当前子组件个数："+c.getList().size());
            }
        });



        //给按钮添加单击事件
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                Button b4 = new Button("b4");
//                group.getChildren().add(b4);
                getGroup(group);
//                b2.setPrefWidth();
            }
        });

        Scene scene = new Scene(group);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    /**
     * 获取子组件
     * @Author created by barrett in 2019/9/23 22:19
     */
    public void getGroup(Group group){
        //可获取组件list
        Object[] objects = group.getChildren().toArray();
        /*for (Object o : objects){
            Button btn = (Button) o;
            btn.setPrefWidth(200);//这里改变宽度
        }*/
        if(objects.length > 0){
            //获取第一个 b1 按钮
            Button btn = (Button) objects[0];
            btn.setPrefWidth(btn.getPrefWidth()+10);
        }
    }
}
