package com.jonoon.clubapp.model.bean;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/20 14:36
 */
public class SquadItem implements Comparable{

    private String id;
    private String group;
    private String name;
    private String avatar;
    private String no;
    private String position;
    private String birthday;
    private String jointime;
    private String origteam;
    private String intro;
    private String zan;
    private String view;

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getNo() {
        return no;
    }

    public String getPosition() {
        return position;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getJointime() {
        return jointime;
    }

    public String getOrigteam() {
        return origteam;
    }

    public String getIntro() {
        return intro;
    }

    public String getZan() {
        return zan;
    }

    public String getView() {
        return view;
    }

    @Override
    public int compareTo(Object o) {
        return this.getGroup().compareTo(((SquadItem) o).getGroup());
    }
}
