package com.jonoon.clubapp.util.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.AndroidHttpClient;
import android.os.Build;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.jonoon.clubapp.model.constants.DefaultValues;
import com.jonoon.clubapp.util.storage.LruBitmapCache;


/**
 * Created by runzhang.han on 2014/11/10.
 */
public class VolleyHelper {
    
    private static VolleyHelper mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mCtx;

    private VolleyHelper(Context context) {
        mCtx = context;
    }

    /*
    * 返回VolleyHelper实例
    * */
    public static synchronized VolleyHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    /*
    * 获取RequestQueue
    * */
    public RequestQueue getRequestQueue() {

        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());

            // Instantiate the cache
            Cache cache = new DiskBasedCache(mCtx.getCacheDir(), DefaultValues.volleyDiskCacheSize);

            Network network = new BasicNetwork(generateHttpStack());

            // Instantiate the RequestQueue with the cache and network.
            mRequestQueue = new RequestQueue(cache, network);

            // Start the queue
            mRequestQueue.start();
        }
        return mRequestQueue;
    }

    /**
     * setup HttpStack according to the Build.VERSION
     * 注意：HttpClientStack不支持重写和ssl加密传输数据
     * 因此，android 2.3以下版本使用ssl传输时，避免使用volley
     * */
    private HttpStack generateHttpStack(){

        HttpStack stack;
        // If the device is running a version >= Gingerbread...
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            stack = new HurlStack();
        } else {
            // ...use AndroidHttpClient for stack.
            stack = new HttpClientStack(AndroidHttpClient.newInstance("volley"));
        }
        return stack;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    /*
    * 获取ImageLoader
    * */
    public ImageLoader getImageLoader() {

        mImageLoader = new ImageLoader(getRequestQueue(),
                new ImageLoader.ImageCache() {
                    private final LruBitmapCache cache = new LruBitmapCache(mCtx);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
        return mImageLoader;
    }

}
