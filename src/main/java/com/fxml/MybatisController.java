package com.fxml;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;

public class MybatisController  {

    //执行结果
    public void perform(){

        try {
            String str = content.getText();
            String[] split = str.split("\\n");
            String sql = split[0].replace("==>  Preparing:","");
            String param = split[1];
            param = param.split("==> Parameters:")[1];
            String[] arr = param.split(",");
            List<String> list = new ArrayList<>();
            for (String s : arr) {
    //            String par = s.substring(0,s.lastIndexOf("("));
                String[] split1 = s.split("[(]");
                String par = split1[0];
                String type = split1[1];
                if(hasNumber(type)){
                    sql=sql.replaceFirst("[?]",par.trim());
                }else{
                    sql=sql.replaceFirst("[?]","'"+par.trim()+"'");
                }
    //            System.out.println(par);
    //            list.add(par);
            }

            result.setText(sql);
        } catch (Exception e) {
            message.setText("啊·哦，系统奔溃了~");
            message.setTextFill(Color.RED);
            e.printStackTrace();
        }
    }

    /**
     * 复制结果集，（据码友 反应 linux 中会报错，还未验证）
     * @author created by barrett in 2020/11/13 15:56
     **/
    public void copyResult(){
        // TODO Auto-generated method stub
        setSysClipboardText(result.getText());
    }

    /**
     * 清空输入框
     * @author created by barrett in 2020/11/13 16:17
     **/
    public void clear(){
       content.setText("");
       message.setText("");
    }

    /**
     * 复制demo
     * @author created by barrett in 2020/11/13 16:17
     **/
    public void copyDemo(){
       setSysClipboardText(demoStr.getText());
       message.setText("复制成功！");
       message.setTextFill(Paint.valueOf("#1AC225"));
    }

    /**
     * 将字符串复制到剪切板。
     */
    public static void setSysClipboardText(String writeMe) {
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable tText = new StringSelection(writeMe);
        clip.setContents(tText, null);
    }

    /**
     * 是否数字类型
     * @author created by barrett in 2020/11/13 10:10
     **/
    public static boolean hasNumber(String str){
        boolean flag = false;
        if(str.contains("Integer")){
            flag=true;
        }else if(str.contains("Long")){
            flag=true;
        }else if(str.contains("Double")){
            flag=true;
        }else if(str.contains("Float")){
            flag=true;
        }else if(str.contains("Short")){
            flag=true;
        }
        return flag;
    }

    @FXML
    private TextArea content;
    @FXML
    private TextArea result;
    @FXML
    private TextArea demoStr;

    @FXML
    private Label message;
}
