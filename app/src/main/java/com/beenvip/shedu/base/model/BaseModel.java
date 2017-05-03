package com.beenvip.shedu.base.model;


import com.beenvip.shedu.base.bean.BaseErrorInfo;

import java.util.Map;

/**
 * Created by sxshi on 2016/12/22
 */

public interface BaseModel<M> {
    interface OnRequestFinishedListener<T> {
        void onError(BaseErrorInfo baseErrorInfo);

        void onSuccess(T response);
    }

    void request(String url, Map<String, Object> paramers, Class<M> clzz, OnRequestFinishedListener<M> listener);
}