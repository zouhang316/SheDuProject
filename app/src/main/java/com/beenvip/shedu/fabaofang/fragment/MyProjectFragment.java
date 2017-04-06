package com.beenvip.shedu.fabaofang.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;

/**
 * Created by ZH on 2017/4/5.
 * 497239511@qq.com
 */

public class MyProjectFragment extends BaseFragment implements View.OnClickListener{
    private TextView bzgl;
    private TextView xmcygl;
    private TextView jhgl;
    private TextView rwgl;
    private TextView xmsz;
    private TextView zlgl;
    private TextView xmlm;
    private TextView xmdt;
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        bzgl= (TextView) view.findViewById(R.id.banzu);
        xmcygl= (TextView) view.findViewById(R.id.chenyuanguanli);
        jhgl= (TextView) view.findViewById(R.id.jihuaguanli);
        rwgl= (TextView) view.findViewById(R.id.renwuguanli);
        xmsz= (TextView) view.findViewById(R.id.xiangmushezhi);
        zlgl= (TextView) view.findViewById(R.id.ziliaoguanli);
        xmlm= (TextView) view.findViewById(R.id.xiangmulianmeng);
        xmdt= (TextView) view.findViewById(R.id.xiangmudongtai);
        initListener();

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_myproject;
    }
    private void initListener(){
        bzgl.setOnClickListener(this);
        xmcygl.setOnClickListener(this);
        jhgl.setOnClickListener(this);
        rwgl.setOnClickListener(this);
        xmsz.setOnClickListener(this);
        zlgl.setOnClickListener(this);
        xmlm.setOnClickListener(this);
        xmdt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.banzu:
                break;
            case R.id.chenyuanguanli:
                break;
            case R.id.jihuaguanli:
                break;
            case R.id.renwuguanli:
                break;
            case R.id.xiangmushezhi:
                break;
            case R.id.ziliaoguanli:
                break;
            case R.id.xiangmulianmeng:
                break;
            case R.id.xiangmudongtai:
                break;
        }

    }
}
