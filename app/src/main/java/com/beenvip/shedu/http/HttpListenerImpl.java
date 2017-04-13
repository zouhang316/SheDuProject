package com.beenvip.shedu.http;

import android.text.TextUtils;

import com.beenvip.shedu.base.HttpBaseResponseBean;
import com.beenvip.shedu.utils.LalaLog;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:impl HttpListener 实现网络请求数据的统一解析
 */

public abstract class HttpListenerImpl implements HttpListener<HttpBaseResponseBean> {
    public HttpListenerImpl(String response) {
        if (TextUtils.isEmpty(response)) LalaLog.e(LalaLog.SSX_TAG, "服务器返回数据为空！");

    }
}
