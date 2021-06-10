package com.study_component.menu;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * MenuBar：窗体最上方的栏目（最小化，最大化，关闭等）
 * Menu：menubar下方的按钮，例：idea的 file、Edit、View 等
 * MenuItem：menu中的下拉菜单
 * @Author created by barrett in 2019/9/30 13:44
 */
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #ccc");

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Edit");
        Menu menu3 = new Menu("View");
        Menu menu4 = new Menu("Navigte");
        //拆分菜单
        Menu menu5 = new Menu("Other Settings");
        //添加菜单
        menuBar.getMenus().addAll(menu1,menu2,menu3,menu4);

        //TODO 菜单图标
        ImageView imgFolder = new ImageView("icon/Open.png");
        imgFolder.setFitHeight(16);
        imgFolder.setFitWidth(16);

        MenuItem item1 = new MenuItem("New");
        MenuItem item2 = new MenuItem("Open...",imgFolder);
        MenuItem item3 = new MenuItem("Open URL...");
        MenuItem item10 = new MenuItem("Close Project");
        MenuItem item11 = new MenuItem("Settings");
        MenuItem item12 = new MenuItem("Project Structure...");

        MenuItem item4 = new MenuItem("Undo Typing",new ImageView("icon/redo.png"));
        MenuItem item5 = new MenuItem("Redo",new ImageView("icon/undo.png"));
        MenuItem item6 = new MenuItem("Cut",new ImageView("icon/cut.png"));


        MenuItem item20 = new MenuItem("Settings for New Projects...");
        MenuItem item21 = new MenuItem("Structure for New Projects...");

        //TODO 禁用选项
        item1.setDisable(true);
        //TODO 菜单栏不可见
//        item2.setVisible(false);

        //TODO 分割线
        SeparatorMenuItem separ1 = new SeparatorMenuItem();

        //TODO 菜单快捷键
        item1.setAccelerator(KeyCombination.valueOf("ctrl+i"));
        item11.setAccelerator(KeyCombination.valueOf("ctrl+alt+s"));

        //TODO 菜单列表中添加菜单列表
        menu5.getItems().addAll(item20,item21);

        //增加菜单下拉列表
        menu1.getItems().addAll(item1,item2,item3,item10,separ1,item11,item12,menu5);

        //TODO 菜单的下拉列表中单选框
        ToggleGroup tg = new ToggleGroup();
        RadioMenuItem rmi1 = new RadioMenuItem("单选-1");
        RadioMenuItem rmi2 = new RadioMenuItem("单选-2");
        RadioMenuItem rmi3 = new RadioMenuItem("单选-3");
        rmi1.setToggleGroup(tg);
        rmi2.setToggleGroup(tg);
        rmi3.setToggleGroup(tg);
        //设置默认选中
        rmi2.setSelected(true);

        //监听单选事件
        rmi1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("rmi1的选中状态 = "+rmi1.isSelected());
                System.out.println("rmi2的选中状态 = "+rmi2.isSelected());
                System.out.println("rmi3的选中状态 = "+rmi3.isSelected());
            }
        });

        menu2.getItems().addAll(item4,item5,item6,rmi1,rmi2,rmi3);

        //TODO 菜单列表多选组件，单击事件与单选组件一样
        CheckMenuItem cmi1 = new CheckMenuItem("多选项1");
        CheckMenuItem cmi2 = new CheckMenuItem("多选项2");
        CheckMenuItem cmi3 = new CheckMenuItem("多选项2");
        menu3.getItems().addAll(cmi1,cmi2,cmi3);


        //TODO menu 增加监听事件，在显示下拉列表时触发(还有隐藏、隐藏中等)
        menu1.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println("menu1");
            }
        });

        //TODO 给item增加监听
        item1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("item1");
            }
        });

        anchorPane.getChildren().add(menuBar);
        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX");
        primaryStage.setHeight(800);
        primaryStage.setWidth(800);
        primaryStage.show();

        //TODO 设置menuBar 的宽度跟随窗口宽度
        menuBar.setPrefWidth(anchorPane.getWidth());
        //设置监听事件，menuBar 宽度跟随窗口宽度调整
        anchorPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                menuBar.setPrefWidth(newValue.doubleValue());
            }
        });
    }
}
