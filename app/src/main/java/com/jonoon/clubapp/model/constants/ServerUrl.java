package com.jonoon.clubapp.model.constants;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/20 9:25
 */
public class ServerUrl {

    private final static String SERVER_ADD = "http://jonoon.ig28.com/";
    private final static String FIXTURE_LIST = "api/fixture";

    private final static String SQUAD_LIST = "api/squad";


    public static String getSERVER_ADD() {
        return SERVER_ADD;
    }

    public static String getFIXTURE_LIST() {
        return SERVER_ADD + FIXTURE_LIST;
    }
    public static String getSQUAD_LIST() {
        return SERVER_ADD + SQUAD_LIST;
    }


}
