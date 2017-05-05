package com.beenvip.shedu.fabaofang.model;

import java.util.List;

/**
 * Created by ZH on 2017/5/5.
 * 497239511@qq.com
 */

public interface FindBanzuModel {
    interface OnMyDataListener{
        void onSuccess(List<String> stringList);
    }

    void getMyData(OnMyDataListener listener);
}
