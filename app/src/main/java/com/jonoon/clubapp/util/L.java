package com.jonoon.clubapp.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/13 13:04
 */

public class L {

    public static void toast(Context con, String text){
        Toast.makeText(con, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * error log
     * **/
    public static void e(String TAG,String msg){
        if(Flags.DEBUG){
            Log.e(TAG, msg);
        }
    }
    /**
     * info log
     * **/
    public static void i(String TAG,String msg){
        if(Flags.DEBUG){
            Log.i(TAG,msg);
        }
    }
    /**
     * debug log
     * **/
    public static void d(String TAG,String msg){
        if(Flags.DEBUG){
            Log.d(TAG,msg);
        }
    }
    /**
     * warning log
     * **/
    public static void w(String TAG,String msg){
        if(Flags.DEBUG){
            Log.w(TAG,msg);
        }
    }

}
