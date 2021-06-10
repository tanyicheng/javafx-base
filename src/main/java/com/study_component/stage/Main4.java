package com.study_component.stage;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 窗口模态：2个窗口依赖关系，a窗口没有关闭时不能关闭b窗口
 * @Author created by barrett in 2019/9/22 21:28
 */
public class Main4 extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        //主窗口
        Stage s1 = new Stage();
        s1.setTitle("S1");
        s1.show();

        //副窗口
        Stage s2 = new Stage();
        s2.setTitle("S2");
        //设置拥有者，关联2个窗口关系
        s2.initOwner(s1);
        s2.initModality(Modality.WINDOW_MODAL);

//        s2.initModality(Modality.APPLICATION_MODAL);//阻止所有窗口，
        s2.show();

//        Stage s3 = new Stage();
//        s3.setTitle("S3");
//        s3.show();
    }
}
