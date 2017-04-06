package com.beenvip.shedu.httputils;

import android.content.Context;

/**
 * Created by Administrator on 2017/3/14 0014.
 */

public class EHUErrorHandler {
    public static void onError(Context context,int reason){
        ToastUtil.show(context,"服务器出错，请稍后重试！\n错误码："+reason);
    }
    public static void onNetworkError(Context context){
        ToastUtil.show(context,"网络异常，请检查网络后重试！");
    }
}
