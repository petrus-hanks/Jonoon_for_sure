package com.jonoon.clubapp.controller;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.jonoon.clubapp.controller.activity.MainActivity;
import com.jonoon.clubapp.controller.activity.WebViewActivity;
import com.jonoon.clubapp.util.L;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/28 14:18
 */
public class MyJavaScriptCallback {

    private final String TAG = this.getClass().getSimpleName();
    public static final String INTERFACE_NAME = "androidCallback";

    public static final String GO_TO_MODULE = "goToModule";

    private BaseActivityWithJavaScriptInterface mActivity;

    public MyJavaScriptCallback(BaseActivityWithJavaScriptInterface act){
        mActivity = act;
    }

    @JavascriptInterface
    public void goBack(){
        L.e(TAG,"goBack");
        mActivity.finish();
    }

    @JavascriptInterface
    public void goToUrl(String url){
        Intent intent = new Intent(mActivity.getApplicationContext(), WebViewActivity.class);
        intent.putExtra(WebViewActivity.URL, url);
        mActivity.startActivity(intent);
    }


    @JavascriptInterface
    public void setGoToModule(String module){

        int index = 1;

        if("index".equals(module)){
            index = 1;
        }else if("squad".equals(module)){
            index = 2;
        }else if("club".equals(module)){
            index = 3;
        }else if("schoolfootball".equals(module)){
            index = 4;
        }else if("bbslist".equals(module)){
            index = 5;
        }else if("newslist".equals(module)){
            index = 6;
        }else if("fixture".equals(module)){
            index = 7;
        }else if("mall".equals(module)){
            index = 8;
        }else if("rank".equals(module)){
            index = 9;
        }
        Intent intent = new Intent(mActivity.getApplicationContext(), MainActivity.class);
        intent.putExtra(GO_TO_MODULE, index);
        mActivity.startActivity(intent);
    }

    @JavascriptInterface
    public void upLoadImage(){

        Intent pickPictureIntent = new Intent(Intent.ACTION_PICK);
        pickPictureIntent.setType("image/*");
        mActivity.startActivityForResult(pickPictureIntent,  BaseActivityWithJavaScriptInterface.REQUEST_IMAGE_URI);
    }
//    case index://首页
//    case squad://足球队
//    case club://俱乐部
//    case schoolfootball://校园足球
//    case bbslist://微社区
//    case newslist://新闻
//    case fixture://赛程
//    case mall://商城
//    case rank://排行榜
}
