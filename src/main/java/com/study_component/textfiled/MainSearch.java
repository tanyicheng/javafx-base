package com.study_component.textfiled;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 关键字查找和排序
 *
 * @Author created by barrett in 2019/10/5 15:29
 */
public class MainSearch extends Application {

    String areaStr = "";
    int idx;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane an = new AnchorPane();
        HBox hBox = new HBox();
        Button find_btn = new Button("查找");
        Button sort_btn = new Button("排序");

        TextField find_field = new TextField();
        TextArea area = new TextArea();
        area.setWrapText(true);//是否自动换行
        area.setText("123456asdqweradsfyuyuopljhgmnbvcxzadfglkjhg");
        AnchorPane.setTopAnchor(area, 50.0);

        hBox.getChildren().addAll(find_field, find_btn, sort_btn);
        an.getChildren().addAll(hBox, area);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(500);
        primaryStage.setWidth(800);
        primaryStage.show();

        //查询关键字
        find_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                area.getParagraphs().forEach(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) {
                        //文本域中的内容
                        String val = charSequence.toString();
                        String find_val = find_field.getText();

                        areaStr = val.substring(idx);

                        //如果文本域中包含查找的字符
                        if (areaStr.contains(find_val)) {
                            //获取焦点
                            find_field.requestFocus();
                            int i = areaStr.indexOf(find_val);
                            int temp = i+idx;
                            idx = temp + find_val.length();
                            System.out.println(temp + "      " + areaStr);
                            //选中关键字
                            area.selectRange(temp, idx);
                        }else{
                            //如果查找完以后，重新初始化
                            areaStr=val;
                            idx=0;
                        }
                    }
                });
            }
        });

        //点击排序
        sort_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                char[] arr = area.getText().toCharArray();
                List<String> list = new ArrayList<>();
                for(char c : arr){
                    list.add(String.valueOf(c));
                }
                //先清除内容
                area.clear();
                //todo 有时间再理解下 stream() 方法
                list.stream()
                        //排序
                        .sorted((item1,item2)->item1.compareTo(item2))
                        //遍历追加字符
                        .forEach(item->area.appendText(item));
            }
        });
    }
}
