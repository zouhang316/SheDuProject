package com.beenvip.shedu.fabaofang.presenter;

import android.content.Context;

import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.base.model.BaseModel;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;
import com.beenvip.shedu.fabaofang.model.FindBanzuModel;
import com.beenvip.shedu.fabaofang.model.FindBanzuModelImpl;
import com.beenvip.shedu.fabaofang.view.FindBanzuView;
import com.beenvip.shedu.utils.LalaLog;

import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2017/5/4.
 * 497239511@qq.com
 */

public class FindBanzuPresenterImpl implements FindBanzuPresenter<BanzuListBean>,BaseModel.OnRequestFinishedListener<BanzuListBean>,FindBanzuModel.OnMyDataListener {
    private FindBanzuView findBanzuView;
    private BaseModel findBanzuModel;
    private FindBanzuModel model;

    public FindBanzuPresenterImpl(FindBanzuView findBanzuView,Context context ){
        this.findBanzuView = findBanzuView;
        findBanzuModel=new FindBanzuModelImpl(context);
        model=new FindBanzuModelImpl(context);
    }

    @Override
    public void getBanzuData(String url, Map paramers, Class clazz, boolean isShowloading) {
        if (findBanzuView!=null){
            if (isShowloading) findBanzuView.showProgress();
        }
        findBanzuModel.request(url,paramers,clazz,this);
    }

    @Override
    public void getMyData() {
        model.getMyData(this);
    }

    @Override
    public void onDestory() {
        findBanzuView=null;
    }

    @Override
    public void onSuccess(BanzuListBean response) {
        if (findBanzuView!=null){
            findBanzuView.hidePregress();
            findBanzuView.showListview(response);
        }
    }

    @Override
    public void onError(BaseErrorInfo baseErrorInfo) {
        LalaLog.d(baseErrorInfo.toString());
        if (findBanzuView!=null){
            findBanzuView.hidePregress();
            findBanzuView.showErrorAlert(baseErrorInfo);
        }

    }

    @Override
    public void onSuccess(List<String> stringList) {
        if (findBanzuView!=null) findBanzuView.showPickView(stringList);
    }
}
