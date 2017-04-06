package com.beenvip.shedu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.beenvip.shedu.base.BaseFragment;
import com.beenvip.shedu.home.ChoiceIdentityActivity;

/**
 * Created by ZH on 2017/3/21.
 * 497239511@qq.com
 */

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private RelativeLayout switchidentity;
    private ScrollView scrollView;
    @Override
    public void initData(Bundle savedInstanceState) {
    }

    @Override
    public void initView(View view, Bundle savedInstanceState) {
        switchidentity= (RelativeLayout) view.findViewById(R.id.switchidentity);
        scrollView= (ScrollView) view.findViewById(R.id.myscrollview);
        switchidentity.setOnClickListener(this);

    }

    @Override
    public int getFragmentLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.switchidentity :
                Intent intent=new Intent(getActivity(), ChoiceIdentityActivity.class);
                startActivity(intent);
                break;
        }
    }


}
