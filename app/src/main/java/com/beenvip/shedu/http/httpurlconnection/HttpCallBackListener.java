package com.beenvip.shedu.http.httpurlconnection;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:Describe the function  of the current class
 */

public interface HttpCallBackListener<T> {
    void onSuccess(T t);
    void onFailed(T t);
}
