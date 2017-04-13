package com.beenvip.shedu.http;

/**
 * @author sxshi on 2017/4/13.
 *         email:emotiona_xiaoshi@126.com
 *         describe:Describe the function  of the current class
 */

public interface HttpListener<T> {
    void onSuccess(T t);
    void onFailed(T t);
}
