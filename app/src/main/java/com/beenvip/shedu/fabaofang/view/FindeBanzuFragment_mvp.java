package com.beenvip.shedu.fabaofang.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.base.bean.BaseErrorInfo;
import com.beenvip.shedu.fabaofang.adapter.FindBanzuAdapter;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;
import com.beenvip.shedu.fabaofang.presenter.FindBanzuPresenter;
import com.beenvip.shedu.fabaofang.presenter.FindBanzuPresenterImpl;
import com.beenvip.shedu.utils.LalaLog;
import com.beenvip.shedu.view.LoadMoreListView;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZH on 2017/5/4.
 * 497239511@qq.com
 */

public class FindeBanzuFragment_mvp extends BaseFragment implements FindBanzuView,LoadMoreListView.MyLoadingListener,
        SwipeRefreshLayout.OnRefreshListener,View.OnClickListener {

    private LoadMoreListView listView;
    private SwipeRefreshLayout refreshLayout;
    private FindBanzuPresenter banzuPresenter;
    private OptionsPickerView pickerView;
    private TextView nianxian;
    @Override
    public void initData(Bundle savedInstanceState) {
        banzuPresenter=new FindBanzuPresenterImpl(this,getActivity());
        Map<String,String> paramers=new HashMap<>();
        paramers.put("mid","6");
        banzuPresenter.getBanzuData(ApiContacts.LIST,paramers, BanzuListBean.class,true);
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        nianxian= (TextView) view.findViewById(R.id.nianxian);
        listView= (LoadMoreListView) view.findViewById(R.id.listview);
        refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.refreshlayout);
        refreshLayout.setColorSchemeResources(R.color.colorPrimaryDark,R.color.radiogroupTextColor,R.color.grenn,R.color.black);
        listView.setInterface(this);
        refreshLayout.setOnRefreshListener(this);
        nianxian.setOnClickListener(this);
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_findbanzu;
    }


    @Override
    public void showListview(BanzuListBean listBean) {
        FindBanzuAdapter adapter=new FindBanzuAdapter(getActivity(),listBean.getData());
        listView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        showLoading();
    }

    @Override
    public void hidePregress() {
        dismissLoading();
    }

    @Override
    public void showErrorAlert(BaseErrorInfo baseErrorInfo) {
        showToast(baseErrorInfo.ErrorInfo);
    }

    @Override
    public void showPickView(final List<String> stringList) {
        pickerView=new OptionsPickerView.Builder(getActivity(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                nianxian.setText(stringList.get(options1));
            }
        }).build();
        pickerView.setPicker(stringList);
        pickerView.show();
    }

    @Override
    public void onLoad() {
        LalaLog.d("加载更多");
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.nianxian:
                banzuPresenter.getMyData();
                break;
        }
    }
}
