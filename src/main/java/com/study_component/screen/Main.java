package com.study_component.screen;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * 屏幕
 * 关系：
 * 第一层 stage
 * 第二层 scene
 * 第三层 node 各种组件
 * @Author created by barrett in 2019/9/22 15:34
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //获取屏幕
        Screen screen = Screen.getPrimary();

        double dpi = screen.getDpi();
        System.out.println("图像每英寸长度内的像素点数: "+dpi);
        //获取所有屏幕
        Rectangle2D rec1 = screen.getBounds();
        //全部屏幕宽高和坐标
        System.out.println("左上角x="+rec1.getMinX()+"    右上角y="+rec1.getMinY());
        System.out.println("左下角x="+rec1.getMaxX()+"    右下角y="+rec1.getMaxY());
        System.out.println("宽度="+rec1.getWidth()+"    高度="+rec1.getHeight());

        //获取可视区域的屏幕
        Rectangle2D rec2 = screen.getVisualBounds();
        System.out.println("左上角x="+rec2.getMinX()+"    右上角y="+rec2.getMinY());
        System.out.println("左下角x="+rec2.getMaxX()+"    右下角y="+rec2.getMaxY());
        System.out.println("宽度="+rec2.getWidth()+"    高度="+rec2.getHeight());


        Platform.exit();
    }
}
