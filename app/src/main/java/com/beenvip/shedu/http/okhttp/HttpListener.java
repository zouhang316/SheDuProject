package com.beenvip.shedu.http.okhttp;

import com.android.volley.VolleyError;

public interface HttpListener<T> {
    /**
     * 服务器响应成功
     *
     * @param result 响应的理想数据。
     */
    void onSuccess(T result);

    /**
     * 网络交互过程中发生错误
     *
     * @param error {@link VolleyError}
     */
    void onError(VolleyError error);
}
