package com.jonoon.clubapp.model.bean;

import com.jonoon.clubapp.util.DateHelper;

import java.util.Objects;

/**
 * @Description: started with 'is':1 for true, 0 for false
 * @Author: runzhang.han
 * @Time: 2015/5/19 10:30
 */
public class FixtureItem implements Comparable{

    private String id;
    private String flag;
    private String league;
    private String team1;
    private String team1logo;
    private String team1score;
    private String team2;
    private String team2logo;
    private String team2score;
    private String addr;
    private String time;
    private String view;
    private String ishow;
    private String isstart;
    private String isend;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam1logo() {
        return team1logo;
    }

    public void setTeam1logo(String team1logo) {
        this.team1logo = team1logo;
    }

    public String getTeam1score() {
        return team1score;
    }

    public void setTeam1score(String team1score) {
        this.team1score = team1score;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getTeam2logo() {
        return team2logo;
    }

    public void setTeam2logo(String team2logo) {
        this.team2logo = team2logo;
    }

    public String getTeam2score() {
        return team2score;
    }

    public void setTeam2score(String team2score) {
        this.team2score = team2score;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getUnixTime() {
        return time;
    }

    public String getTime() {
        return DateHelper.getTimeString(Long.parseLong(time));
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getIshow() {
        return ishow;
    }

    public void setIshow(String ishow) {
        this.ishow = ishow;
    }

    public String getIsstart() {
        return isstart;
    }

    public void setIsstart(String isstart) {
        this.isstart = isstart;
    }

    public String getIsend() {
        return isend;
    }

    public void setIsend(String isend) {
        this.isend = isend;
    }

    public String getYear() {
        return DateHelper.getYearString(Long.parseLong(time));
    }

    public String getMonthOfEnglishName() {return DateHelper.getMonthEnglishString(Long.parseLong(time));}

    public String getWeek() {
        return DateHelper.getWeek(Long.parseLong(time));
    }

    public String getDate() {
        return DateHelper.getDate(Long.parseLong(time));
    }


    @Override
    public int compareTo(Object o) {
        return this.getUnixTime().compareTo(((FixtureItem) o).getUnixTime());
    }
}
