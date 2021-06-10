package com.study_component.listview;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * setOnDragDetected(new EventHandler<MouseEvent>());
 * 当你从一个Node上进行拖动的时候，会检测到拖动操作，将会执行这个EventHandler。
 *
 * setOnDragEntered(new EventHandler<DragEvent>());
 * 当你拖动到目标控件的时候，会执行这个事件回调。
 *
 * setOnDragExited(new EventHandler<DragEvent>());
 * 当你拖动移出目标控件的时候，执行这个操作。
 *
 * setOnDragOver(new EventHandler<DragEvent>());
 * 当你拖动到目标上方的时候，会不停的执行。
 *
 * setOnDragDropped(new EventHandler<DragEvent>());
 * 当你拖动到目标并松开鼠标的时候，执行这个DragDropped事件。
 *
 * setOnDragDone(new EventHandler<DragEvent>());
 * 当你拖动并松手的时候，执行Drag完成操作。
 *
 * 这里要注意的是，首先执行拖动，拖动到目标控件的时候，会首先执行DragEnter，这个只执行一遍。然后当你停留在目标控件上方的时候，会不停的指定DragOver操作。
 * 参考链接 https://www.wingmei.cn/2014/12/25/javafx%E4%B9%8B%E7%BB%84%E4%BB%B6%E6%8B%96%E6%94%BE%E6%93%8D%E4%BD%9C/
 * 实现拖拽排序
 * 鼠标悬停显示
 * @author created by barrett in 2021/5/25 14:26
 **/
public class ListViewDrag extends Application{
	int index;
	String data;
	public static void main(String[] args){
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		AnchorPane an = new AnchorPane();
		String s1 = "A先生-18-男";
		String s2 = "B先生-18-男";
		String s3 = "C先生-18-男";
		String s4 = "D先生-18-男";
		ObservableList<String> list = FXCollections.observableArrayList();	
		list.addAll(s1,s2,s3,s4);

		ListView<String> listview = new ListView<String>();
		listview.setPrefWidth(200);
		//占位符 当listview没有数据时显示占位符
		listview.setPlaceholder(new Label("没有数据"));
		//添加一个可观测的列表显示
		listview.setItems(list);
		listview.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			int position = 0;
			String positionstr = "";
			@Override
			public ListCell<String> call(ListView<String> param) {
				// TODO Auto-generated method stub
				Label label = new Label();

				ListCell<String> cell = new ListCell<String>(){
					@Override
					protected void updateItem(String item, boolean empty) {

						super.updateItem(item, empty);
						if(empty == false){
							label.setText(item);
							label.setPrefHeight(20);
							label.setFont(new Font(15.0));
							this.setGraphic(label);
						}
					}
				};

				//鼠标移上去放大显示
                cell.hoverProperty().addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
                                        Boolean newValue) {
                        if(newValue == true && label.getText().equals("") != true){
                            position = param.getItems().indexOf(label.getText());
                            label.setPrefHeight(40.0);
                            label.setFont(new Font(22));
                            param.getFocusModel().focus(position);
                        }else{
                            label.setPrefHeight(20);
                            label.setFont(new Font(15.0));
                        }

                    }
                });

				//设置如何处理拖拽元素，先删除拖拽位置元素，再将其添加在拖拽位置
				cell.setOnDragDropped(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						// TODO Auto-generated method stub
						if(position == -1){
							position = param.getItems().size()-1;
						}
						String value = event.getDragboard().getString();
						param.getItems().remove(index);
						param.getItems().add(position,value);
						System.out.println(value);
						param.getSelectionModel().select(position);
					}
				});

				//设置拖拽鼠标的样式
				cell.setOnDragOver(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						// TODO Auto-generated method stub
						event.acceptTransferModes(TransferMode.MOVE);

					}
				});

				//实时获取拖拽位置
				cell.setOnDragEntered(new EventHandler<DragEvent>() {
					@Override
					public void handle(DragEvent event) {
						// TODO Auto-generated method stub
						position = param.getItems().indexOf(label.getText());
						positionstr = label.getText();
						param.getFocusModel().focus(position);//得到焦点

						//System.out.println(position + "-" + label.getText() );
					}
				});

				//创建拖拽面板加入选中拖拽元素
				cell.setOnDragDetected(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						Dragboard db = cell.startDragAndDrop(TransferMode.MOVE);
						ClipboardContent content = new ClipboardContent();
						content.putString(data);
						db.setContent(content);
					}
				});
				return cell;
			}
		});
		//设置单元格尺寸
		listview.setFixedCellSize(50);

		//标签拖动
		Label drag = new Label("拖我试试");//拖动的组件
		drag.setPrefWidth(200);
		drag.setPrefHeight(100);
		drag.setStyle("-fx-border-color: #00ff");
		Label drop = new Label("放这里试试");//下放的组件
		drop.setPrefWidth(200);
		drop.setPrefHeight(100);
		drop.setStyle("-fx-border-color: #00ff");

		//拖动图片
		ImageView imageView = new ImageView("icon/apple.png");
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		imageDrag(imageView);

		//拖动文件
		TextArea textArea = new TextArea("拖动文件到这里");
		textArea.setPrefWidth(400);
		textDrag(textArea,imageView);

		an.getChildren().addAll(listview,drag,drop,imageView,textArea);

		labelDrag(drag,drop);

		AnchorPane.setTopAnchor(listview, 10.0);
		AnchorPane.setLeftAnchor(listview, 10.0);

		AnchorPane.setTopAnchor(drag, 10.0);
		AnchorPane.setLeftAnchor(drag, 250.0);

		AnchorPane.setTopAnchor(drop, 10.0);
		AnchorPane.setLeftAnchor(drop, 500.0);

		AnchorPane.setTopAnchor(imageView, 150.0);
		AnchorPane.setLeftAnchor(imageView, 250.0);

		AnchorPane.setTopAnchor(textArea, 300.0);
		AnchorPane.setLeftAnchor(textArea, 250.0);

		Scene scene = new Scene(an);
		primaryStage.setScene(scene);
		primaryStage.setTitle("javafx");
		primaryStage.setHeight(800);
		primaryStage.setWidth(800);
		primaryStage.show();

		listview.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				index = newValue.intValue();
				System.out.println(index);
			}
		});
		listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				data = newValue;
			}
		});
	}


	public void textDrag(TextArea textArea,ImageView imageView){
		textArea.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != imageView) {
					event.acceptTransferModes(TransferMode.ANY);
				}
			}
		});

		textArea.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				List files = dragboard.getFiles();
				if(files.size() > 0){
					//将文本内容去除
					textArea.setText(getContent((File) files.get(0)));
				}
			}
		});
	}

	public static String getContent(File file) {
		try(FileInputStream fis = new FileInputStream(file)) {
			byte[] b = new byte[(int) file.length()];
			fis.read(b);
			return new String(b, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 从电脑中拖动图片进来
	 * @author created by barrett in 2021/6/10 20:31
	 **/
	public void imageDrag(ImageView imageView){
		imageView.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != imageView) {
					event.acceptTransferModes(TransferMode.ANY);
				}
			}
		});

		imageView.setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				List files = dragboard.getFiles();
				if(files.size() > 0){
					try {
						System.out.println(files.get(0));
						imageView.setImage(new Image(new FileInputStream((File) files.get(0))));
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * 标签拖动案例
	 * @author created by barrett in 2021/6/10 20:29
	 **/
	public void labelDrag(Label drag, Label drop){
		drag.setOnDragDetected(new EventHandler() {

			@Override
			public void handle(Event event) {
				Dragboard dragboard = drag.startDragAndDrop(TransferMode.ANY);
				ClipboardContent content = new ClipboardContent();
				content.putString(drag.getText());
				dragboard.setContent(content);
			}
		});

		drag.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				// TODO Auto-generated method stub
				event.acceptTransferModes(TransferMode.MOVE);
			}
		});

		drop.setOnDragEntered(new EventHandler() {
			@Override
			public void handle(Event event) {
				drop.setTextFill(Color.RED);
			}
		});

		drop.setOnDragExited(new EventHandler() {
			@Override
			public void handle(Event event) {
				drop.setTextFill(Color.BLACK);
			}
		});

		drop.setOnDragOver(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				if (event.getGestureSource() != drop && event.getDragboard().hasString()) {
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});
		drop.setOnDragDropped(new EventHandler<DragEvent>() {
			@Override
			public void handle(DragEvent event) {
				Dragboard dragboard = event.getDragboard();
				drop.setText(dragboard.getString());
			}
		});
//        drag.setOnDragDone(new EventHandler<DragEvent>() {
//
//            @Override
//            public void handle(DragEvent event) {
//                a221.setText(a111.getText());
//            }
//        });
	}

}