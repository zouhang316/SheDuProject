package com.beenvip.shedu.httputils;


/**
 * Created by Administrator on 2017/2/23 0023.
 */

public interface IJSONCallback<T> {
    public void onSuccess(int what,T response);
    public void onError(int reason);
    public void onNetworkCrashed();
}
