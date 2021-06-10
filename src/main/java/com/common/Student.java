package com.common;

import java.beans.PropertyChangeSupport;

public class Student {
    private String name;
    private int age;
    private double score;

    //对student 对象进行维护，所以传入this
    public PropertyChangeSupport psc = new PropertyChangeSupport(this);

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        //发出通知
        psc.firePropertyChange("SetNamePro",oldValue,this.name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
