package com.beenvip.shedu.fabaofang.model;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.base.model.BaseModel;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;
import com.beenvip.shedu.http.okhttp.HttpClient;
import com.beenvip.shedu.http.okhttp.HttpErrorParser;
import com.beenvip.shedu.http.okhttp.HttpListener;
import com.beenvip.shedu.http.okhttp.HttpRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2017/5/4.
 * 497239511@qq.com
 */

public class FindBanzuModelImpl implements BaseModel<BanzuListBean> ,FindBanzuModel{
    private HttpClient httpClient;

    public FindBanzuModelImpl(Context context) {
        httpClient=HttpClient.getInstance(context);
    }

    @Override
    public void request(String url, Map<String, Object> paramers, Class<BanzuListBean> clzz, final OnRequestFinishedListener<BanzuListBean> listener) {
        HttpRequest httpRequest=new HttpRequest.Builder(url).setMethod(Request.Method.GET).addParam(paramers).build();
        httpClient.gsonRequest(clzz, httpRequest, new HttpListener<BanzuListBean>() {
            @Override
            public void onSuccess(BanzuListBean result) {
                if (result.isResult()){
                    listener.onSuccess(result);
                }else {
                    String erroCode=result.getErrorCode();
                    String erroInfo=result.getErrorInfo();
                    BaseErrorInfo baseErrorInfo=new BaseErrorInfo(erroCode,erroInfo);
                    listener.onError(baseErrorInfo);
                }
            }

            @Override
            public void onError(VolleyError error) {
                    listener.onError(new HttpErrorParser(error));
            }
        },this);
   }


    @Override
    public void getMyData(OnMyDataListener listener) {
        List<String> stringList=new ArrayList<>();
        stringList.add("周一");
        stringList.add("周二");
        stringList.add("周三");
        stringList.add("周四");
        stringList.add("周五");
        stringList.add("周六");
        listener.onSuccess(stringList);
    }
}
