package com.jonoon.clubapp.model.variable;

import com.jonoon.clubapp.model.bean.SquadItem;

import java.util.ArrayList;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/20 20:17
 */
public class SquadGroupData {

    private String groupName;
    private ArrayList<SquadItem> children = new ArrayList<SquadItem>();

    public String getGroupName() {
        return groupName;
    }

    public ArrayList<SquadItem> getChildren() {
        return children;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
