package com.beenvip.shedu.publics.activity;

import android.os.Bundle;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;

/**
 * Created by ZH on 2017/5/5.
 * 497239511@qq.com
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting);
        setTitle("设置");
    }
}
