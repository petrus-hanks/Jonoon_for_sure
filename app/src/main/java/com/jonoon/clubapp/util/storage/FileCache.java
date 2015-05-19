package com.jonoon.clubapp.util.storage;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jonoon.clubapp.MyApplication;
import com.jonoon.clubapp.util.L;
import com.jonoon.clubapp.util.StringHelper;


import java.io.File;

public class FileCache {
	
	private final static boolean DEBUG = false;
    private static final String TAG = FileCache.class.getName();
 
    public static final int CONFIG_CACHE_MOBILE_TIMEOUT  = 3600000;  //1 hour
    public static final int CONFIG_CACHE_WIFI_TIMEOUT    = 300000;   //5 minute

    public static String getUrlCache(String url) {

        if (StringHelper.isEmpty(url)) {
            return null;
        }
 
        String result = null;

        File file = new File(MyApplication.getmCacheDir() + "/" + getCacheDecodeString(url));
        L.e(TAG, "LocalUrl:" + MyApplication.getmCacheDir() + "/" + getCacheDecodeString(url));
        
        if (file.exists() && file.isFile()) {	
            result = FileUtils.readTextFile(file);
        }else{
        	L.e(TAG, "! file.exists() &&! file.isFile()");
        	return null;
        }
        return result;
    }
 
    public static void setUrlCache(String data, String url){

        File file = new File(MyApplication.getmCacheDir() + "/" + getCacheDecodeString(url));
        //创建缓存数据到磁盘，就是创建文件
        FileUtils.writeTextFile(file, data);
        L.e(TAG, "setUrlData:"+data);
    }
 
    public static String getCacheDecodeString(String url) {
        //1. 处理特殊字符
        //2. 去除后缀名带来的文件浏览器的视图凌乱(特别是图片更需要如此类似处理，否则有的手机打开图库，全是我们的缓存图片)
        if (!StringHelper.isEmpty(url)) {
            return url.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
        }
        return null;
    }

    /**
     * 根据@param：url获取cache，并转为target类返回
     * */
    public static <T> T getDataFromCache(String url, java.lang.Class<T> target){

        String cache = FileCache.getUrlCache(url);
        L.e(TAG,"cache = "+cache);
        if(cache == null){
            //TODO 异常处理，缓存为空怎么办？
            return null;
        }
        T data = null;
        try {
            Gson gson = new Gson();
            data = gson.fromJson(cache,target);
        }catch (JsonSyntaxException exception){
            exception.printStackTrace();
        }
        return data;
    }
}