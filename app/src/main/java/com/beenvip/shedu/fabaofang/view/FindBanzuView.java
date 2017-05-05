package com.beenvip.shedu.fabaofang.view;

import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;

import java.util.List;

/**
 * Created by ZH on 2017/5/4.
 * 497239511@qq.com
 */

public interface FindBanzuView {
    void showListview(BanzuListBean listBean);
    void showErrorAlert(BaseErrorInfo baseErrorInfo);
    void showPickView(List<String> stringList);
    void showProgress();
    void hidePregress();
}
