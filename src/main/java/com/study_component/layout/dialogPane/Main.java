package com.study_component.layout.dialogPane;

import javafx.application.Application;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 * 对话框，不推荐
 * 推荐使用以下方法
 * Dialog TextInputDialog
 * Dialog ChoiceDialog
 * Dialog Alert
 *
 * @Author created by barrett in 2019/9/28 21:41
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button btn = new Button("点击打开窗口");

        AnchorPane ap = new AnchorPane();
        ap.setStyle("-fx-background-color: #ccc");
        AnchorPane.setTopAnchor(btn, 100.0);
        AnchorPane.setLeftAnchor(btn, 100.0);

        Scene scene = new Scene(ap);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //对话框
                DialogPane dialogPane = new DialogPane();
                dialogPane.setHeaderText("标题文字");
                dialogPane.setContentText("正文");
                //添加按钮
                dialogPane.getButtonTypes().add(ButtonType.CLOSE);
                dialogPane.getButtonTypes().add(ButtonType.CANCEL);

                dialogPane.setExpandableContent(new Text("扩展内容"));
                //默认不展开扩展内容
                dialogPane.setExpanded(false);

                //按钮添加事件
                Button btn = (Button) dialogPane.lookupButton(ButtonType.CANCEL);
                Button btn2 = (Button) dialogPane.lookupButton(ButtonType.CLOSE);

                //右侧添加图片
                ImageView im = new ImageView("file:icon/close.png");
                im.setFitWidth(50);
                im.setFitHeight(50);
                dialogPane.setGraphic(im);

                Stage stage = new Stage();
                //给 stage 设置场景，关联 dialogPane
                Scene sc = new Scene(dialogPane);
                stage.setScene(sc);

                stage.setTitle("对话框窗口");
                //设置窗口标题只有关闭按钮
                stage.initStyle(StageStyle.UTILITY);
                //设置窗口关联
                stage.initOwner(primaryStage);
                //设置模态类型
                stage.initModality(Modality.WINDOW_MODAL);
                //设置是否可调整窗口大小
                stage.setResizable(false);
                stage.show();

                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("取消");
                    }
                });
                btn2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("关闭");
                        stage.close();
                    }
                });

                //启动调度任务，需要设定计划（时间）才能运行
                MyScheduledService scheduledService = new MyScheduledService(dialogPane,stage);
                //什么时候开始启动任务
                scheduledService.setDelay(Duration.millis(0));
                //每秒运行一次任务
                scheduledService.setPeriod(Duration.millis(1000));

                scheduledService.start();
            }
        });


        ap.getChildren().addAll(btn);

        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}

//自定义调度任务
class MyScheduledService extends ScheduledService<Integer> {

    int i = 0;
    private DialogPane dialogPane = null;
    private Stage stage = null;

    public MyScheduledService() {
    }

    public MyScheduledService(DialogPane dialogPane, Stage stage) {
        this.dialogPane = dialogPane;
        this.stage = stage;
    }

    @Override
    protected Task<Integer> createTask() {
        Task<Integer> task = new Task<Integer>() {
            //TODO 这里执行的不是主线程，不能更新组件信息,可以做一些其他任务
            @Override
            protected Integer call() throws Exception {
                System.out.println("call = " + Thread.currentThread().getName());
                i += 1;
                return i;//TODO 这里返回的值给到 updateValue()的入参中
            }

            @Override
            protected void updateValue(Integer value) {//这里的入参来自 call的返回值
                System.out.println("updateValue = " + Thread.currentThread().getName()+";  value：" + value);

                if(value < 10){
                    dialogPane.setContentText(value.toString());
                }else{
                    //这里需要指定调度类，否则无法取消调度任务
                    MyScheduledService.this.cancel();
                    stage.close();
                }
            }
        };
        return task;
    }
}