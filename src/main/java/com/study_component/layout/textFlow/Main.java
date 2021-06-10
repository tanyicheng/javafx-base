package com.study_component.layout.textFlow;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * 放入的文本在窗体缩放是自动换行
 * @Author created by barrett in 2019/9/28 14:02
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        Text t1 = new Text("这里是第一段");
        Text t2 = new Text("中国人民解放军军人");
        Text t3 = new Text("我想当特警");

        //字体样式等设置
        t2.setFont(Font.font(20));
        t2.setStyle("-fx-fill: #f00");//设置颜色
        t2.setFill(Paint.valueOf("#f00"));//设置颜色

        TextFlow textFlow = new TextFlow();
        textFlow.setStyle("-fx-background-color: #eb6");
        //对齐方式：靠左
//        textFlow.setTextAlignment(TextAlignment.LEFT);
        //行间距
//        textFlow.setLineSpacing(20);
        textFlow.getChildren().addAll(t1,t2,t3);


        TextFlow textFlow2 = new TextFlow();
        textFlow2.getChildren().addAll(new Text("我是另一个TextFlow"));
        //设置第一个textFlow的位置
        anchorPane.setTopAnchor(textFlow,100.0);
        anchorPane.setLeftAnchor(textFlow,100.0);//左边间距100

        anchorPane.getChildren().addAll(textFlow,textFlow2);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();

        primaryStage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //TODO 这里不能用 getPreWidth，因为在没有设置PreWidth时，width默认会有父组件的宽度，
                System.out.println("AnchorPane的宽度："+anchorPane.getWidth());
                System.out.println("textFlow的宽度："+textFlow.getWidth());
            }
        });

        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //TODO 这里需要注意textflow 左侧有间距，需要减掉
                textFlow.setPrefWidth(newValue.doubleValue()-100);
            }
        });
    }
}
