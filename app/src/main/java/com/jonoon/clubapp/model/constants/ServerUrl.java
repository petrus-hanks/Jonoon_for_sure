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


    private final static String INDEX = "app/index.html";
    private final static String FIXTURE_ITEM = "app/fixture.html";
    private final static String SQUAD_ITEM = "app/squad.html";

    private final static String NEWS = "app/newslist.html";
    private final static String MALL = "app/mall.html";
    private final static String CLUB = "app/club.html";
    private final static String PRODUCT = "app/product.html";

    private final static String MEMBER = "app/member.html";
    private final static String CAMPUS_FOOTBALL = "app/schoolfootball.html";
    private final static String BBS = "app/bbslist.html";



    public static String getSERVER_ADD() {
        return SERVER_ADD;
    }

    public static String getIndex() {
        return SERVER_ADD + INDEX;
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

    public static String getNewsList(){return SERVER_ADD + NEWS;}

    public static String getMall(){return SERVER_ADD + MALL;}
    public static String getClub(){return SERVER_ADD + CLUB;}
    public static String getProductDetail(){return SERVER_ADD + PRODUCT;}

    public static String getFixtureItem(){return SERVER_ADD + FIXTURE_ITEM;}
    public static String getUserCenter(){return SERVER_ADD + MEMBER;}
    public static String getCampusFootball(){return SERVER_ADD + CAMPUS_FOOTBALL;}
    public static String getBBS(){return SERVER_ADD + BBS;}

//    http://jonoon.ig28.com/app/fixture_data.html?id=n 赛场详情 数据统计
//    http://jonoon.ig28.com/app/fixture_imagelist.html?id=n 赛场详情 图集
//    http://jonoon.ig28.com/app/fixture_videos.html?id=n 赛场详情 视频列表
//    http://jonoon.ig28.com/app/fixture_living.html?id=n 赛场详情 直播
//    http://jonoon.ig28.com/app/fixture_report.html?id=n 赛场详情 赛况报道
//    http://jonoon.ig28.com/app/fixture_scoreguess.html?id=n 赛场详情 比分竞猜
//    http://jonoon.ig28.com/app/fixture_scoreresult.html?id=n 赛场详情 比分竞猜

//    http://jonoon.ig28.com/app/login.html 登录注册页面

//    http://jonoon.ig28.com/app/member.html 会员中心
//    http://jonoon.ig28.com/app/member_orderlist.html 我的订单列表
//    http://jonoon.ig28.com/app/member_orderdetail.html 我的订单详情
//    http://jonoon.ig28.com/app/member_favorites.html 收藏夹
//    http://jonoon.ig28.com/app/member_info.html 修改资料
//    http://jonoon.ig28.com/app/member_viewhistory.html 浏览记录

//    http://jonoon.ig28.com/app/club.html 俱乐部首页

//    http://jonoon.ig28.com/app/bbslist.html 微社区

//    http://jonoon.ig28.com/app/bbsdetail.html 话题详情
//    http://jonoon.ig28.com/app/bbspost.html 发新话题

//    http://jonoon.ig28.com/app/newslist.html 新闻首页

//    http://jonoon.ig28.com/app/news.html 新闻详情

//    http://jonoon.ig28.com/app/club.html 俱乐部首页
//    http://jonoon.ig28.com/app/schoolfootball.html 校园足球首页



}
