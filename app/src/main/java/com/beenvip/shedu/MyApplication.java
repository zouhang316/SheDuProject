package com.beenvip.shedu;

import android.app.Application;

import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * Created by ZH on 2017/3/22.
 * 497239511@qq.com
 */

public class MyApplication extends Application {
    /**
     * 存储验证码倒计时时间
     */
    public static HashMap<String, Long> longHashMap;
    public static boolean isUseHTTPS=false;//是否是Https请求
    @Override
    public void onCreate() {
        super.onCreate();
        LalaLog.setDebug(true);
    }
}
