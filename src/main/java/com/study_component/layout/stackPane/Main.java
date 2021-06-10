package com.study_component.layout.stackPane;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * 图层：一本书摞在一起的感觉
 *
 * @Author created by barrett in 2019/9/28 13:42
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Button b1 = new Button("Barrett1");
        Button b2 = new Button("Barrett2");
//        b2.setPrefWidth(200);
        Button b3 = new Button("Barrett3");
        Button b4 = new Button("Barrett4");
        Button b5 = new Button("Barrett5");
        Button b6 = new Button("Barrett6");
        Button b7 = new Button("Barrett7");

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #eb6");
        stackPane.setPadding(new Insets(10));
        stackPane.setAlignment(Pos.BOTTOM_LEFT);
        stackPane.setMargin(b1, new Insets(50));


        stackPane.getChildren().addAll(b1, b2, b3, b4, b5, b6, b7);

        //获取所有布局在stack上的组件
        /*stackPane.getChildren().forEach(new Consumer<Node>() {
            @Override
            public void accept(Node node) {
                Button bu = (Button) node;
                System.out.println(bu.getText());
            }
        });*/

        stackPane.getChildren().forEach(item -> {
            Button bu = (Button) item;
            System.out.println(bu.getText());
        });

        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.setTitle("JavaFX");
        primaryStage.show();
    }
}
