package com.beenvip.shedu.user.presenter;

import java.util.Map;

/**
 * @author sxshi on 2016/12/22.
 * @email:emotiona_xiaoshi@126.com
 * @describe:Describe the function  of the current class
 */

public interface LoginPresenter <T>{
    void onLogin(String url, Map<String, Object> paramers, Class<T> clzz, boolean isShowLoading);

    void onDestory();
}
