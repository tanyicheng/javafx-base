package com.study_component.choiceBox;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StudentSimple {
    //此类型自带监听，会监听值是否发生改变
    private SimpleStringProperty name = new SimpleStringProperty();
    private SimpleIntegerProperty age = new SimpleIntegerProperty();
    private SimpleDoubleProperty score = new SimpleDoubleProperty();

    public StudentSimple() {
    }

    public StudentSimple(String name, int age, double score) {
        this.name.setValue(name);
        this.age.setValue(age);
        this.score.setValue(score);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public SimpleIntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public double getScore() {
        return score.get();
    }

    public SimpleDoubleProperty scoreProperty() {
        return score;
    }

    public void setScore(double score) {
        this.score.set(score);
    }


}
