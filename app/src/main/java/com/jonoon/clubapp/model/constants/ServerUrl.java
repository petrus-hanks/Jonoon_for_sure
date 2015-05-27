package com.jonoon.clubapp.model.constants;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/20 9:25
 */
public class ServerUrl {

    private final static String SERVER_ADD = "http://jonoon.ig28.com/";
    private final static String FIXTURE_LIST = "fixture";
    private final static String SQUAD_LIST = "squad";

    private final static String SQUAD_ITEM = "app/squad.html";

    private final static String NEWS = "app/news.html";
    private final static String MALL = "app/mall.html";
    private final static String CLUB = "app/club.html";
    private final static String PRODUCT = "app/product.html";

    public static String getSERVER_ADD() {
        return SERVER_ADD;
    }

    public static String getFIXTURE_LIST() {
        return SERVER_ADD + FIXTURE_LIST;
    }
    public static String getSQUAD_LIST() {
        return SERVER_ADD + SQUAD_LIST;
    }
    public static String getSquadItem() {
        return SERVER_ADD + SQUAD_ITEM;
    }

    public static String getNews(){return SERVER_ADD + NEWS;}

    public static String getMall(){return SERVER_ADD + MALL;}
    public static String getClub(){return SERVER_ADD + CLUB;}
    public static String getProductDetail(){return SERVER_ADD + PRODUCT;}






}
