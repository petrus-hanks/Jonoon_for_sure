package com.jonoon.clubapp.model.bean;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/20 14:39
 */
public class Squad {

    private String code;
    private String msg;
    private ArrayList<SquadItem> data;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ArrayList<SquadItem> getData() {
        return data;
    }
}
