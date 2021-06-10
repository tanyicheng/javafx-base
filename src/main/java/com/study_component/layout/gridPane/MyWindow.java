package com.study_component.layout.gridPane;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * 登录成功后显示的窗口
 * @Author created by barrett in 2019/9/29 21:30
 */
public class MyWindow {
    private final Stage state = new Stage();

    public MyWindow(String name,String password) {

        Text text = new Text();
        text.setText("账号："+name+"    密码："+password);
        BorderPane borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #ccc");

        borderPane.setCenter(text);

        Scene scene = new Scene(borderPane);
        state.setScene(scene);
        state.setTitle("控制台");
        state.setWidth(800);
        state.setHeight(500);
        state.show();
    }
}
