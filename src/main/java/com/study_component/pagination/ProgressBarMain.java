package com.study_component.pagination;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * 无拖拽的进度条 ProgressIndicator
 * @Author created by barrett in 2019/11/26 21:53
 */
public class ProgressBarMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    ScheduledService<Double> scheduledService;
    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();

        //圆形
        ProgressIndicator pi = new ProgressIndicator(0.4);
        pi.setPrefHeight(100.0);
        pi.setPrefWidth(100.0);

        //长条
        ProgressBar pb = new ProgressBar(0.8);
        pb.setStyle("-fx-background-color: #ccc");
        pb.setPrefHeight(10.0);
        pb.setPrefWidth(200.0);

        //来回走动
//        pb.setProgress(ProgressBar.INDETERMINATE_PROGRESS);

        //位置
        pb.setLayoutY(100.0);
        pb.setLayoutX(100.0);

        pi.setLayoutY(200.0);
        pi.setLayoutX(100.0);

        pb.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                System.out.println(newValue);
            }
        });



        an.getChildren().addAll(pb,pi);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();

        scheduledService = new ScheduledService<Double>() {
            double i = 0;
            @Override
            protected Task<Double> createTask() {
                Task<Double> task = new Task<Double>() {
                    @Override
                    protected Double call() throws Exception {
                        //每次加0.1
                        return i += 0.1;
                    }

                    @Override
                    protected void updateValue(Double value) {
                        super.updateValue(value);
                        pb.setProgress(value);
                        pi.setProgress(value);
                        if(value > 1){
                            scheduledService.cancel();
                            System.out.println("任务取消");
                        }
                    }
                };

                return task;
            }

        };


        //任务启动
        scheduledService.setDelay(Duration.millis(0));//从第0秒开始
        scheduledService.setPeriod(Duration.millis(1000));//每秒执行
        scheduledService.start();
    }
}
