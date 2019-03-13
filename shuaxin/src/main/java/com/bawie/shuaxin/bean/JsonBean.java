package com.bawie.shuaxin.bean;

import java.util.ArrayList;

/**
 * @Author：asus
 * @E-mail： 945574298@163.com
 * @Date：2019/3/13 19:36
 * @Description：描述信息
 */
public class JsonBean {
    private String code;
    private ArrayList<One> data;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<One> getData() {
        return data;
    }

    public void setData(ArrayList<One> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
