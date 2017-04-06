package com.beenvip.shedu.httputils;

/**
 * Created by Administrator on 2017/2/23 0023.
 */

public interface IConnCallBack {
    public void onSuccess(String str);
    public void onError(int reason);
    public void onNetworkCrashed();
}
