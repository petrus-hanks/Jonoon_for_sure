package com.jonoon.clubapp.controller;


import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.webkit.JavascriptInterface;

import com.jonoon.clubapp.R;
import com.jonoon.clubapp.controller.activity.MainActivity;
import com.jonoon.clubapp.controller.activity.WebViewActivity;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.storage.FileUtils;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/28 14:18
 */
public class MyJavaScriptCallback {

    private final String TAG = this.getClass().getSimpleName();
    public static final String INTERFACE_NAME = "androidCallback";

    public static final String GO_TO_MODULE = "goToModule";

    private FragmentActivity mActivity;

    public MyJavaScriptCallback(FragmentActivity act){
        mActivity = act;
    }

    @JavascriptInterface
    public void goBack(){
        L.e(TAG,"goBack");
        mActivity.finish();
    }

    @JavascriptInterface
    public void goToUrl(String url){
        L.e(TAG,"goToUrl "+url);
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
        }else if("user".equals(module)){
            index = 11;
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

    @JavascriptInterface
    public void showShare(String title, String text, String url) {
        L.e(TAG,"showShare js interface");
        ShareSDK.initSDK(mActivity);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
//        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(url);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(FileUtils.copyPrivateRawResourceToPubliclyAccessibleFile(mActivity, R.raw.logo, "share_icon"));
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(url);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mActivity.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(url);

// 启动分享GUI
        oks.show(mActivity);
    }

//    case wechat 微信
//    case qq QQ
//    case weibo 新浪微博
    @JavascriptInterface
    public void login(String plat) {

        Platform platform = null;
        if("wechat".equals(plat)){
            platform= ShareSDK.getPlatform(mActivity, Wechat.NAME);
        }else if("qq".equals(plat)){
            platform= ShareSDK.getPlatform(mActivity, QZone.NAME);
        }else if("weibo".equals(plat)){
            platform= ShareSDK.getPlatform(mActivity, SinaWeibo.NAME);
        }

        if (platform == null) {
            return;
        }
//判断指定平台是否已经完成授权
        if(platform.isValid()) {
            String userId = platform.getDb().getUserId();
            if (userId != null) {
//                UIHandler.sendEmptyMessage(MSG_USERID_FOUND, this);
//                login(plat.getName(), userId, null);
                L.e("","DB userid="+userId );
                return;
            }
        }
        platform.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                L.e("","onComplete");
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                L.e("","onError");
            }

            @Override
            public void onCancel(Platform platform, int i) {
                L.e("","onCancel");
            }
        });
        // true不使用SSO授权，false使用SSO授权
        platform.SSOSetting(false);
        //获取用户资料
        platform.showUser(null);
    }
}
