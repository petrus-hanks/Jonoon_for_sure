package com.jonoon.clubapp.view.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/15 10:04
 */
public class H5WebView extends WebView {

    private Context mContext;

    private final String TAG = this.getClass().getSimpleName();
    private WaitingDialog waiting;

    private void init(Context context) {
        mContext = context;

        waiting = new WaitingDialog(mContext);
        // Configure the webview
        WebSettings s = getSettings();
        s.setBuiltInZoomControls(false);
        s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        s.setUseWideViewPort(true);

        s.setLoadWithOverviewMode(true);
        s.setJavaScriptEnabled(true);

        setWebViewClient(mWebViewClient);

        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

    }

    private WebViewClient mWebViewClient = new WebViewClient(){
        @Override
        public void  onPageStarted (WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
            waiting.show();
        }
        @Override
        public void  onPageFinished (WebView view, String url) {
            super.onPageFinished(view, url);
            waiting.hide();
        }
    };

    @Override
    public void onPause() {
        waiting.cancel();
        super.onPause();
    }

    //fragment来管理web的后退动作
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK) {
//            if(canGoBack()){
//                goBack();
//                return true;
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public H5WebView(Context context) {
        super(context);
        init(context);
    }

    public H5WebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public H5WebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
}
