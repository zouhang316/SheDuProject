package com.beenvip.shedu.supplier;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.activity.PersonInforActivity;
import com.beenvip.shedu.contractor.activity.SMActivity;
import com.beenvip.shedu.publics.ChoiceIdentityActivity;
import com.beenvip.shedu.publics.KefuActivity;

/**
 * Created by ZH on 2017/3/21.
 * 497239511@qq.com
 */

public class SupplierMineFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout switchidentity;
    private RelativeLayout userInfo;
    private RelativeLayout smyz;
    private RelativeLayout kefu;
    private ScrollView scrollView;
    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        switchidentity= (RelativeLayout) view.findViewById(R.id.switchidentity);
        scrollView= (ScrollView) view.findViewById(R.id.myscrollview);
        userInfo= (RelativeLayout) view.findViewById(R.id.contractor_userinfo);
        smyz= (RelativeLayout) view.findViewById(R.id.smyz);
        kefu= (RelativeLayout) view.findViewById(R.id.kefu);
        initListener();
    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_supplier_mine;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.switchidentity :
                startActivity(new Intent(getActivity(), ChoiceIdentityActivity.class));
                break;
            case R.id.contractor_userinfo:
                startActivity(new Intent(getActivity(), PersonInforActivity.class));
                break;
            case R.id.smyz:
                startActivity(new Intent(getActivity(), SMActivity.class));
                break;
            case R.id.kefu:
                startActivity(new Intent(getActivity(), KefuActivity.class));
                break;
        }
    }
    private void initListener(){
        switchidentity.setOnClickListener(this);
        userInfo.setOnClickListener(this);
        smyz.setOnClickListener(this);
        kefu.setOnClickListener(this);
    }


}