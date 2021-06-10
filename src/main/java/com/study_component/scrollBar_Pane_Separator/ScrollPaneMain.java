package com.study_component.scrollBar_Pane_Separator;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 滚动面板
 * @Author created by barrett in 2019/12/4 10:45
 */
public class ScrollPaneMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox box = new HBox();
        VBox vbox = new VBox();

        Button btn = new Button("点击滚动");
        box.setStyle("-fx-background-color: #ccc");
//        box.setLayoutX(100);
//        box.setLayoutY(100);

        for(int i = 1; i <= 10;i++){
            vbox.getChildren().add(new Button("button"+i));
        }
        box.getChildren().add(vbox);

        for(int i = 1; i <= 10;i++){
            box.getChildren().add(new Button("button"+i));
        }

        ScrollPane pane = new ScrollPane();
        pane.setContent(box);
        pane.setPrefWidth(300);
        pane.setPrefHeight(200);


        pane.hvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("水平"+newValue);
            }
        });

        pane.vvalueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                System.out.println("垂直"+newValue);
            }
        });

//        AnchorPane.setLeftAnchor(btn,300.0);

        an.getChildren().addAll(pane);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();


    }
}
