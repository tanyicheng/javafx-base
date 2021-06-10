package com.study_component.buttonBar;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 组件大小默认以最大的尺寸同步其他组件
 * 实现事件接口，批量设置监听事件
 * @Author created by barrett in 2019/10/6 13:54
 */
public class Main extends Application implements EventHandler {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        ButtonBar bar = new ButtonBar();

        Button b1 = new Button("APPLY");
        Button b2 = new Button("NO");
        Button b3 = new Button("YES");

        ButtonBar.setButtonData(b1, ButtonBar.ButtonData.APPLY);
        ButtonBar.setButtonData(b2, ButtonBar.ButtonData.NO);
        ButtonBar.setButtonData(b3, ButtonBar.ButtonData.YES);

        b1.setPrefWidth(200);
        //可以单独设置宽度，不影响其他组件大小
        ButtonBar.setButtonUniformSize(b1,false);

        bar.getButtons().addAll(b1,b2,b3);

        //设置排序，不同操作系统，显示不同
//        bar.setButtonOrder(ButtonBar.BUTTON_ORDER_MAC_OS);

        an.getChildren().addAll(bar);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        b1.setOnAction(this);
        b2.setOnAction(this);
        b3.setOnAction(this);
    }

    /**
     * 实现监听接口
     * @Author created by barrett in 2019/10/6 14:04
     */
    @Override
    public void handle(Event event) {
        Button source = (Button) event.getSource();
        System.out.println(source.getText()+"       "+ButtonBar.getButtonData(source));
    }
}
