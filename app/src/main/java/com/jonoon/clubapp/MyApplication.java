package com.jonoon.clubapp;

import android.app.Application;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/15 16:38
 */
public class MyApplication extends Application {

    private static String mCacheDir;

    public boolean goToIntroduction = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mCacheDir = this.getCacheDir().getAbsolutePath();
    }

    public static String getmCacheDir() {
        return mCacheDir;
    }

    public static void setmCacheDir(String mCacheDir) {
        MyApplication.mCacheDir = mCacheDir;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
