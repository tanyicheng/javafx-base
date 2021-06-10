package com.example.table;

import com.sun.deploy.util.StringUtils;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TableViewSample extends Application {

    private final TableView<Person> table = new TableView<>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
            new Person("Jacob", "Smith", "jacob.smith@example.com",1),
            new Person("Isabella", "Johnson", "isabella.johnson@example.com",2),
            new Person("Ethan", "Williams", "ethan.williams@example.com",3),
            new Person("Emma", "Jones", "emma.jones@example.com",4),
            new Person("Michael", "Brown", "michael.brown@example.com",5)
            );
    final HBox hb = new HBox();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(500);
        stage.setHeight(550);

        final Label label = new Label("Address Book");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

       /* firstNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        firstNameCol.setOnEditCommit(
            (CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setFirstName(t.getNewValue());
        });*/


        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      /* lastNameCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
       lastNameCol.setOnEditCommit(
            (CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setLastName(t.getNewValue());
        });*/

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.<Person>forTableColumn());
        emailCol.setOnEditCommit((CellEditEvent<Person, String> t) -> {
                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                        ).setEmail(t.getNewValue());
        });


        TableColumn<Person, String> ageCol = new TableColumn<>("Age");
        ageCol.setMinWidth(20);
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));


        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,ageCol);

        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");

        addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(lastNameCol.getPrefWidth());
        addLastName.setPromptText("Last Name");

        final TextField addEmail = new TextField();
        addEmail.setMaxWidth(emailCol.getPrefWidth());
        addEmail.setPromptText("Email");

        final TextField addAge = new TextField();
        addAge.setMaxWidth(emailCol.getPrefWidth());
        addAge.setPromptText("Age");

        final Button addButton = new Button("Add");
        addButton.setOnAction((ActionEvent e) -> {
            data.add(new Person(
                    addFirstName.getText(),
                    addLastName.getText(),
                    addEmail.getText()
                    //fixme 获取不到int类型
//                    Integer.getInteger(addAge.getText())
//                    Integer.getInteger("".equals(addAge.getText()) ? "1" : addAge.getText())
                    ));
            addFirstName.clear();
            addLastName.clear();
            addEmail.clear();
            addAge.clear();
        });

        hb.getChildren().addAll(addFirstName, addLastName, addEmail, addAge,addButton);
        hb.setSpacing(3);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;
//        private final SimpleIntegerProperty age;

        private Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }
        private Person(String fName, String lName, String email,Integer age) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
//            this.age = new SimpleIntegerProperty(age);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }

//        public Integer getAge() {
//            return age.get();
//        }
//
//        public void setAge(Integer age) {
//            this.age.set(age);
//        }
    }
}