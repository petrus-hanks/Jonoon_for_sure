package com.jonoon.clubapp.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by runzhang.han on 2014/10/29.
 */
public class DateHelper {

    public static String getTime(){
        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date curDate = new Date(System.currentTimeMillis());

        String reStr = timeFormatter.format(curDate);
        return reStr;
    }

    //字符串转date
    public static Date getDate(String date_string){

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(date_string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDate(long UNIX_timestamp){


        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd");
        Date curTime = new Date(UNIX_timestamp*1000);

        String reStr = timeFormatter.format(curTime);

        return reStr;
    }

    public static String getTimeString(long UNIX_timestamp){

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
        Date curTime = new Date(UNIX_timestamp*1000);

        String reStr = timeFormatter.format(curTime);

        return reStr;
    }

    public static String getYearString(long UNIX_timestamp){

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyy");
        Date curTime = new Date(UNIX_timestamp*1000);

        String reStr = timeFormatter.format(curTime);

        return reStr;
    }

    public static String getWeek(long UNIX_timestamp){

        SimpleDateFormat timeFormatter = new SimpleDateFormat("EEEE");
        Date curTime = new Date(UNIX_timestamp*1000);

        String reStr = timeFormatter.format(curTime);

        return reStr;
    }

    public static String getMonthEnglishString(long UNIX_timestamp){

        SimpleDateFormat timeFormatter = new SimpleDateFormat("MM");
        Date curTime = new Date(UNIX_timestamp*1000);

        String month = timeFormatter.format(curTime);
        int month_num = Integer.parseInt(month);

        String reStr = new String();
        switch (month_num){
            case 1:
                reStr = "January";
                break;
            case 2:
                reStr = "February";
                break;
            case 3:
                reStr = "March";
                break;
            case 4:
                reStr = "April";
                break;
            case 5:
                reStr = "May";
                break;
            case 6:
                reStr = "June";
                break;
            case 7:
                reStr = "July";
                break;
            case 8:
                reStr = "August";
                break;
            case 9:
                reStr = "September";
                break;
            case 10:
                reStr = "October";
                break;
            case 11:
                reStr = "November";
                break;
            case 12:
                reStr = "December";
                break;
        }

        return reStr;
    }
}
