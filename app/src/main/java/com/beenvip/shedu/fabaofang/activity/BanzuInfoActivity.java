package com.beenvip.shedu.fabaofang.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.fabaofang.activity.fragment.AchievementFragment;
import com.beenvip.shedu.fabaofang.activity.fragment.BanzuInfo_FFragment;
import com.beenvip.shedu.fabaofang.activity.fragment.EvaluateFragment;
import com.beenvip.shedu.fabaofang.bean.BanzuListBean;

/**
 * Created by ZH on 2017/4/21.
 * 497239511@qq.com
 */

public class BanzuInfoActivity extends BaseActivity implements View.OnClickListener{
    private FragmentTransaction ft;
    protected final int INFO_FLAG=0x100;
    protected final int ACHIEVEMENT_FLAG=0x200;
    protected final int EVALUATE_FLAG=0x300;
    private BanzuInfo_FFragment infoFragment;
    private AchievementFragment achievementFragment;
    private EvaluateFragment evaluateFragment;
    private String banzuName;
    private String banzuType;
    private String banzuPhone;
    private TextView mInfo;
    private TextView mAchievement;
    private TextView mEvaluate;
    private TextView indcator_a,indcator_b,indcator_c;
    private TextView name,type,phoneNumber;
    private BanzuListBean.DataBean dataBean;
    @Override
    protected void initData() {
        dataBean=getIntent().getParcelableExtra("data");
    }

    @Override
    protected void initListener() {
        mInfo.setOnClickListener(this);
        mAchievement.setOnClickListener(this);
        mEvaluate.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_banzuinfo);
        mInfo=findView(R.id.myinfo);
        mAchievement=findView(R.id.achievement);
        mEvaluate=findView(R.id.evaluate);
        name=findView(R.id.banzuname);
        type=findView(R.id.banzutype);
        phoneNumber=findView(R.id.phoneNumber);
        indcator_a=findView(R.id.indcator_a);
        indcator_b=findView(R.id.indcator_b);
        indcator_c=findView(R.id.indcator_c);
        name.setText(dataBean.getBName());
        type.setText(dataBean.getBTypename());
        phoneNumber.setText(dataBean.getBPhone());
        setTitle(dataBean.getBName());
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",dataBean);
        showContentFragment(INFO_FLAG,bundle);
    }
    private void hiddenIndcator(){
        indcator_a.setVisibility(View.INVISIBLE);
        indcator_b.setVisibility(View.INVISIBLE);
        indcator_c.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle=new Bundle();
        switch (v.getId()){
            case R.id.myinfo:
                showContentFragment(INFO_FLAG,bundle);
                break;
            case R.id.achievement:
                showContentFragment(ACHIEVEMENT_FLAG,bundle);
                break;
            case R.id.evaluate:
                showContentFragment(EVALUATE_FLAG,bundle);
                break;
        }

    }
    private void showContentFragment(int index,Bundle bundle){
        if (bundle==null){
            bundle=new Bundle();
        }
        ft=getSupportFragmentManager().beginTransaction();
        hideFragment(ft);
        hiddenIndcator();
        switch (index){
            case INFO_FLAG:
                if (infoFragment==null){
                    infoFragment=new BanzuInfo_FFragment();
                    infoFragment.setArguments(bundle);
                    ft.add(R.id.banzu_fl_content,infoFragment);
                }else {
                    ft.show(infoFragment);
                }
                indcator_a.setVisibility(View.VISIBLE);
                break;
            case ACHIEVEMENT_FLAG:
                if (achievementFragment==null){
                    achievementFragment=new AchievementFragment();
                    achievementFragment.setArguments(bundle);
                    ft.add(R.id.banzu_fl_content,achievementFragment);
                }else {
                    ft.show(achievementFragment);
                }
                indcator_b.setVisibility(View.VISIBLE);
                break;
            case EVALUATE_FLAG:
                if (evaluateFragment==null){
                    evaluateFragment=new EvaluateFragment();
                    evaluateFragment.setArguments(bundle);
                    ft.add(R.id.banzu_fl_content,evaluateFragment);
                }else {
                    ft.show(evaluateFragment);
                }
                indcator_c.setVisibility(View.VISIBLE);
                break;
        }
        ft.commit();
    }
    private void hideFragment(FragmentTransaction ft) {
        if (infoFragment != null) {
            ft.hide(infoFragment);
        }
        if (achievementFragment != null) {
            ft.hide(achievementFragment);
        }
        if (evaluateFragment != null) {
            ft.hide(evaluateFragment);
        }
    }
}
