package com.jonoon.clubapp.model.bean;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/19 10:24
 */
public class Fixture {

    private String code;
    private String msg;
    private ArrayList<FixtureItem> data;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<FixtureItem> getData() {
        return data;
    }
}
