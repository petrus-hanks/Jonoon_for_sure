package com.jonoon.clubapp.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class ScreenInfo {
	
	/**屏幕宽*/
	public int width;
	/**屏幕高*/
	public int height;
    /*屏幕像素总数*/
    public int pixel_of_screen;
	/**屏幕密度（0.75 / 1.0 / 1.5）*/
	public float density;
	/**屏幕密度DPI（120 / 160 / 240）*/
	public double densityDpi;

    private static ScreenInfo mInstance;
	
	/**init ScreenInfoHelper*/
	private ScreenInfo(Context context){

	    DisplayMetrics metric = context.getResources().getDisplayMetrics();
		
		height = metric.heightPixels;
		width = metric.widthPixels;  // 屏幕宽度（像素）
		density = metric.density;  
		densityDpi = metric.densityDpi;
        pixel_of_screen = height*width;
	}

    public static synchronized ScreenInfo getInstance(Context context) {

        mInstance = new ScreenInfo(context);
        return mInstance;
    }
}

