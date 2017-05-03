package com.beenvip.shedu.user.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.base.model.BaseModel;
import com.beenvip.shedu.http.okhttp.HttpClient;
import com.beenvip.shedu.http.okhttp.HttpErrorParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;
import com.beenvip.shedu.user.model.bean.LoginBean;

import java.util.Map;

/**
 * @author sxshi on 2017/5/3.
 *         email:emotiona_xiaoshi@126.com
 *         describe:Describe the function  of the current class
 */

public class LoginModelImpl implements BaseModel<LoginBean> {
    private HttpClient httpClient;

    public LoginModelImpl(Context context) {
        httpClient = HttpClient.getInstance(context);
    }

    @Override
    public void request(String url, Map<String, Object> paramers, Class<LoginBean> clzz, final OnRequestFinishedListener<LoginBean> listener) {
        final HttpRequest httpRequest = new HttpRequest.Builder(url).setMethod(Request.Method.GET).addParam(paramers).build();
        httpClient.gsonRequest(clzz, httpRequest, new HttpListener<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (loginBean.isResult()) {
                    listener.onSuccess(loginBean);
                } else {
                    String errCode = loginBean.ErrorCode;
                    String errMsg = loginBean.ErrorInfo;
                    BaseErrorInfo baseErrorInfo = new BaseErrorInfo(errCode, errMsg);
                    listener.onError(baseErrorInfo);
                }
            }

            @Override
            public void onError(VolleyError error) {
                listener.onError(new HttpErrorParser(error));
            }
        }, this);
    }
}
