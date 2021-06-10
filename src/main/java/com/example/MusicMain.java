package com.example;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * 音乐播放器
 * @Author created by barrett in 2019/11/25 22:14
 */
public class MusicMain extends Application {

    public static String path = "file:/F:/music/SmokingLove.mp3";
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        Button bn = new Button("播放");

        Media media = new Media(path);
        MediaPlayer mp = new MediaPlayer(media);


        an.getChildren().addAll(bn);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        bn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mp.play();
            }
        });

    }
}
