package com.study_component.comboBox;

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

/**
 * ComboBox 下拉菜单，item位置固定
 * 带有可编辑功能
 * 如果是操作对象用法参考 choiceBox
 * TODO 第42课，ComboBox和ChoiceBox在修改对象数据内部属性之间的区别：ComboBox清空选择的值，再选择回来即可；ChoiceBox需要移除数组中的选项，然后再set回去，因为没有serFactory()的工厂方法；
 * setConverter防止异常，此类可以指定占位符 cbb.setPromptText("请输入");
 * @Author created by barrett in 2019/10/7 10:20
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();

        ComboBox cbb = new ComboBox();
        ComboBox cbb2 = new ComboBox();
        cbb.getItems().addAll("item1","item2","item3","item4","item5");

        //允许编辑
        cbb.setEditable(true);
        //文本提示，在没有焦点的时候出现
        cbb.setPromptText("请输入");
        //在item没有元素时用作提示
//        cbb.setPlaceholder(new Text("没加载到数据"));

        //显示滚动条
        cbb.setVisibleRowCount(3);

        hBox.getChildren().addAll(cbb,cbb2);
        an.getChildren().addAll(hBox);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

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

    }
}
