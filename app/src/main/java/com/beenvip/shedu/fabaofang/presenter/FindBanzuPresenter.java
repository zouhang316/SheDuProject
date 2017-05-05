package com.beenvip.shedu.fabaofang.presenter;

import java.util.Map;
import java.util.Objects;

/**
 * Created by ZH on 2017/5/4.
 * 497239511@qq.com
 */

public interface FindBanzuPresenter<T> {
    void getBanzuData(String url, Map<String,Objects> paramers,Class<T> clazz,boolean isShowloading);
    void getMyData();
    void onDestory();
}
