package com.study_component.layout.anchorPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 下列几种方法测试
 * setManaged 脱离父级管控，不会消失，但是不占位
 * setVisible 组件消失，但是依然会占位
 * setOpacity 设置透明度
 *
 * @Author created by barrett in 2019/9/26 09:39
 */
public class Managed_Visible_Opactity extends Application {


    static Boolean managed = true;
    static Boolean visible = true;
    static int opacity = 1;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        Button b1 = new Button("Button-1");
        Button b2 = new Button("--------Button-2");
        Button b3 = new Button("Button-3");


        HBox hBox = new HBox();
        hBox.getChildren().addAll(b1, b2, b3);

        Button b5 = new Button("b2.setManaged(" + managed + ")");
        Button b6 = new Button("b2.setVisible(" + visible + ")");
        Button b7 = new Button("b2.setOpacity(" + opacity + ")");

        b5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                managed = !managed;
                b2.setManaged(managed);
                b5.setText("b2.setManaged(" + managed + ")");

                b2.setText("--------Button-2");

            }
        });
        b6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                visible = !visible;
                b2.setVisible(visible);
                b6.setText("b2.setVisible(" + visible + ")");

            }
        });
        b7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(opacity == 1){
                    opacity = 0;
                    b2.setOpacity(opacity);
                    b7.setText("b2.setOpacity(" + opacity + ")");
                }else{
                    opacity = 1;
                    b2.setOpacity(opacity);
                    b7.setText("b2.setOpacity(" + opacity + ")");
                }

            }
        });

//        b2.setManaged(false);
//        b2.setVisible(false);
//        b2.setOpacity(0);
        VBox vBox = new VBox();
        vBox.setLayoutY(100);
        vBox.getChildren().addAll(b5, b6, b7);


        AnchorPane ap = new AnchorPane();

        ap.setStyle("-fx-background-color: #76ccb5");
        ap.getChildren().addAll(hBox, vBox);

        Scene scene = new Scene(ap);
        primaryStage.setScene(scene);

        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(500);
        primaryStage.setWidth(500);
        primaryStage.show();
    }
}
