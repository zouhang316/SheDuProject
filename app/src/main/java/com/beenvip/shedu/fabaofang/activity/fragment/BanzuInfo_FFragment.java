package com.beenvip.shedu.fabaofang.activity.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;

/**
 * Created by ZH on 2017/4/21.
 * 497239511@qq.com
 */

public class BanzuInfo_FFragment extends BaseFragment {
    private BanzuListBean.DataBean dataBean;
    private TextView city,personNumber,contact;
    @Override
    public void initData(Bundle savedInstanceState) {
        dataBean=getArguments().getParcelable("data");
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        city= (TextView) view.findViewById(R.id.city);
        personNumber= (TextView) view.findViewById(R.id.personnumber);
        contact= (TextView) view.findViewById(R.id.contact);
        city.setText(dataBean.getBCityname());
        personNumber.setText(dataBean.getBNumer());
        contact.setText(dataBean.getBContact());
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_banzuinfor_f;
    }
}
