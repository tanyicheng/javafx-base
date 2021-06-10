package com.valueBind.propertyChangeSupport;

import javafx.beans.property.SimpleIntegerProperty;

/**
 * 单向绑定
 * @Author created by barrett in 2020/1/27 10:19
 */
public class OneWayBind {
    public static void main(String[] args) {
        SimpleIntegerProperty x = new SimpleIntegerProperty(1);
        SimpleIntegerProperty y = new SimpleIntegerProperty(5);

        //x绑定在y上面,x不能设置值，
        x.bind(y);
//        x.set(3);
        //通过y的值修改x
        y.set(3);

        System.out.println("x: "+x.get()+"   y: "+y.get());

        //解绑后可以随便赋值
        x.unbind();
        x.set(66);
    }
}
