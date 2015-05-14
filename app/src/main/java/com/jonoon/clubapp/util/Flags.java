package com.jonoon.clubapp.util;

import com.jonoon.clubapp.BuildConfig;

/**
 * @Description:
 * @Author: runzhang.han
 * @Time: 2015/5/13 13:04
 */
public class Flags {

    /**
     * debug开关，关闭debug的方法：1，project-》build automatically设置为否
     * 2，clean 3，export android application
     * 此时，DEBUG = BuildConfig.DEBUG = false
     * *
     */
    public static final boolean DEBUG;

    static {
        DEBUG = BuildConfig.DEBUG;
    }

    /**
     * 用于局部调试需要
     * */
    public static final boolean DEBUG_LIFECYCLE = true?DEBUG:false;

    /**
     * 设置左上角返回按钮是返回主菜单还是返回上一级
     * */
    public static final boolean bIsBackToHome_DEFAULT = false;
    /**
     * 设置是否有tool按钮显示
     * */
    public static final boolean bHasSettingBtn_DEFAULT = false;

}