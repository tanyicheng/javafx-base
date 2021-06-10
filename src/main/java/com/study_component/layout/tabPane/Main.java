package com.study_component.layout.tabPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * TabPane : 标签页，在宽度不够用时会自动折叠标签页
 * @Author created by barrett in 2019/10/1 14:27
 */
public class Main extends Application {


    static int i = 1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane= new AnchorPane();
        TabPane tabPane = new TabPane();
        tabPane.setStyle("-fx-background-color: #eb6");
        tabPane.setPrefHeight(500);
        tabPane.setPrefWidth(500);

        AnchorPane.setTopAnchor(tabPane,100.0);
        AnchorPane.setLeftAnchor(tabPane,100.0);

        Tab tab1 = new Tab("标签页1");
        Tab tab2 = new Tab("标签页2");
        Tab tab3 = new Tab("标签页3");

        HBox box = new HBox();
        box.getChildren().addAll(new Button("Button1"),new Button("Button2"),new Button("Button3"));
        box.setStyle("-fx-background-color: blue");
        box.setAlignment(Pos.CENTER);
        tab1.setContent(box);


        VBox box2 = new VBox();
        box2.getChildren().addAll(new Button("Button6"),new Button("Button5"),new Button("Button7"));
        box2.setStyle("-fx-background-color: green");
        box2.setAlignment(Pos.CENTER);
        tab2.setContent(box2);

        tab3.setContent(new Text("这里是内容"));

        /**
         * 设置标签图标(异常处理 Invalid URL or resource not found ) --- start
         * 错误：原来使用icon/apple.png 就能访问，后面启动就报错了，使用 file:icon/apple.png 不会报错，但是图片不显示
         * 正确的，放在 resource 目录下，还是使用 icon/apple.png 访问即可
         **/
        ImageView img = new ImageView("icon/apple.png");
        img.setFitWidth(20);
        img.setFitHeight(20);
        tab2.setGraphic(img);
        //todo 设置朝向：设置朝向的图片
//        tabPane.setSide(Side.LEFT);
        tabPane.setRotateGraphic(false);//设置图片永远垂直
        // TODO --- end 要想图片设置垂直必须放在 primaryStage.show(); 前面

        //todo 不允许关闭标签页
        tab2.setClosable(false);
        //todo 默认标签页
        tabPane.getSelectionModel().select(tab2);
        //todo 统一给标签页设置不关闭
//        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        //todo 监听
        tabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> observable, Tab oldValue, Tab newValue) {
                System.out.println(newValue.getText());
            }
        });
        //todo 还有 setOnClosed、setOnClosedRequest 同理关闭是都会调用， event.consume()阻止事件传递
        tab1.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Tab source = (Tab) event.getSource();
                System.out.println("选择改变都会触发："+source.isSelected()+"    name："+source.getText());
            }
        });

        anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(">>>>> 新增标签页");
                tabPane.getTabs().add(new Tab("新增页"+i));
                i++;
            }
        });

        tabPane.getTabs().addAll(tab1,tab2,tab3);

        anchorPane.getChildren().addAll(tabPane);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
}
