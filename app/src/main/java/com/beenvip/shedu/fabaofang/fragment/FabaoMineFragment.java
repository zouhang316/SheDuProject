package com.beenvip.shedu.fabaofang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.contractor.activity.PersonInforActivity;
import com.beenvip.shedu.publics.activity.SMActivity;
import com.beenvip.shedu.fabaofang.activity.FabaoActivity;
import com.beenvip.shedu.fabaofang.activity.RecruitmentActivity;
import com.beenvip.shedu.publics.activity.ChoiceIdentityActivity;
import com.beenvip.shedu.publics.activity.KefuActivity;
import com.beenvip.shedu.publics.activity.SettingActivity;

/**
 * Created by ZH on 2017/3/21.
 * 497239511@qq.com
 */

public class FabaoMineFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout switchidentity;
    private RelativeLayout userInfo;
    private RelativeLayout smyz;
    private RelativeLayout kefu;
    private RelativeLayout setting;
    private ScrollView scrollView;
    private Button fabao;
    private Button zhaogong;
    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        switchidentity= (RelativeLayout) view.findViewById(R.id.switchidentity);
        scrollView= (ScrollView) view.findViewById(R.id.myscrollview);
        userInfo= (RelativeLayout) view.findViewById(R.id.contractor_userinfo);
        smyz= (RelativeLayout) view.findViewById(R.id.smyz);
        fabao= (Button) view.findViewById(R.id.fabao);
        zhaogong= (Button) view.findViewById(R.id.zhaogong);
        kefu= (RelativeLayout) view.findViewById(R.id.kefu);
        setting= (RelativeLayout) view.findViewById(R.id.setting);
        initListener();


    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_fabao_mine;
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
            case R.id.fabao:
                startActivity(new Intent(getActivity(), FabaoActivity.class));
                break;
            case R.id.zhaogong:
                startActivity(new Intent(getActivity(), RecruitmentActivity.class));
                break;
            case R.id.kefu:
                startActivity(new Intent(getActivity(), KefuActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingActivity.class));
                break;
        }
    }
    private void initListener(){
        switchidentity.setOnClickListener(this);
        userInfo.setOnClickListener(this);
        smyz.setOnClickListener(this);
        fabao.setOnClickListener(this);
        zhaogong.setOnClickListener(this);
        kefu.setOnClickListener(this);
        setting.setOnClickListener(this);
    }


}
