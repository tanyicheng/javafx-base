package com.study_component.ColorPicker_DatePicker;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * 时间选择器
 *
 * @Author created by barrett in 2019/11/10 10:05
 */
public class DatePickerMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane an = new AnchorPane();

        DatePicker date = new DatePicker(LocalDate.now());
        date.setEditable(false);//设置不可编辑

        date.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {

                DateCell cell = new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        Label label = new Label(String.valueOf(item.getDayOfMonth()));
                        label.setPrefWidth(50.0);
                        this.setText("");//需要原有的日期
                        this.setGraphic(label);
                    }
                };
                return cell;
            }
        });

        date.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {

                System.out.println(newValue);

                int year = newValue.getYear();
                int month = newValue.getMonthValue();
                int day = newValue.getDayOfMonth();
                int week = newValue.getDayOfWeek().getValue();
                int number = newValue.getDayOfYear();

                System.out.println(year+"年 "+month+"月 "+day+"日 "+week+"周 一年中第"+number+"天");
            }
        });

        an.getChildren().addAll(date);
        Scene scene = new Scene(an);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }
}
