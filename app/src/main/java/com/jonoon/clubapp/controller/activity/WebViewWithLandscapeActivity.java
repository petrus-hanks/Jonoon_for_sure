package com.jonoon.clubapp.controller.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.BaseActivityWithJavaScriptInterface;
import com.jonoon.clubapp.controller.MyJavaScriptCallback;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.view.custom_view.H5WebView;

public class WebViewWithLandscapeActivity extends BaseActivityWithJavaScriptInterface {

    public static final String URL = "url_key";
    private final String TAG = this.getClass().getSimpleName();

    private H5WebView wv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        wv = (H5WebView) findViewById(R.id.webview);

        wv.addJavascriptInterface(new MyJavaScriptCallback(this), MyJavaScriptCallback.INTERFACE_NAME);

        String mUrl = getIntent().getStringExtra(URL);
        L.e(TAG, "url = " + mUrl);
        wv.loadUrl(mUrl);
    }

    public boolean canGoBack(){
        return wv.canGoBack();
    }

    public void goBack(){
        if(wv.canGoBack()){
            wv.goBack();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        L.e(TAG, "onPause");
        wv.onPause();
        wv.pauseTimers();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.e(TAG, "onResume");
        wv.onResume();
        wv.resumeTimers();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.e(TAG, "onDestroy");
        wv.stopLoading();
        wv.setWebChromeClient(null);
        wv.setWebViewClient(null);
        wv.destroy();
        wv = null;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        L.e(TAG,"onConfigurationChanged");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(canGoBack()){
                goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
