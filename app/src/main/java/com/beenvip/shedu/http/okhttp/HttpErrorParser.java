package com.beenvip.shedu.http.okhttp;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.beenvip.shedu.base.bean.BaseErrorInfo;

/**
 * @author sxshi on 2016/12/22.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe 统一处理 {@link VolleyError}
 */

public class HttpErrorParser extends BaseErrorInfo {
    private VolleyError mVolleyError;

    public HttpErrorParser(VolleyError volleyError) {
        this.mVolleyError = volleyError;
        this.errMessage = getErrorMsg();
        this.responseCode = getStatusCode();
    }

    public String getErrorMsg() {
        Class mVolleyErrorClass = mVolleyError.getClass();
        String message = null;
        if (mVolleyErrorClass == ServerError.class) {
            message = "服务不可用，请稍后重试!";
        } else if (mVolleyErrorClass == AuthFailureError.class) {
            message = "身份验证未通过，请稍后重试!";
        } else if (mVolleyErrorClass == ParseError.class) {
            message = "解析数据错误，请稍后重试!";
        } else if (mVolleyErrorClass == TimeoutError.class) {
            message = "网络连接超时，请稍后重试！";
        } else if (mVolleyError instanceof NetworkError) {
            if (mVolleyErrorClass == NoConnectionError.class) {
                message = "无法连接服务器,请检查网络地址是否正确!";
            } else {
                message = "网络连接异常，请检查网络!";
            }
        } else {
            message = "未知异常，请稍后重试!";
        }
        return message;
    }

    public String getStatusCode() {
        return mVolleyError.networkResponse == null ? "-1" : mVolleyError.networkResponse.statusCode + "";
    }
}
