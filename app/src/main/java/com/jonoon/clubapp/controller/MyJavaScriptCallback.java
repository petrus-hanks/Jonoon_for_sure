package com.jonoon.clubapp.controller;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

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

    private Activity mActivity;

    public MyJavaScriptCallback(Activity act){
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
}
