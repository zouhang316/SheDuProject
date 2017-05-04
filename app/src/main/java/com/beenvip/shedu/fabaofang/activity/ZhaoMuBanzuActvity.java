package com.beenvip.shedu.fabaofang.activity;

import android.os.Bundle;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.utils.LalaLog;

/**
 * Created by ZH on 2017/4/28.
 * 497239511@qq.com
 */

public class ZhaoMuBanzuActvity extends BaseActivity {
    @Override
    protected void initData() {
        LalaLog.d(getIntent().getStringExtra("pid"));

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
        setContentView(R.layout.activity_zhaomubanzu);
        setTitle("招募班组");

    }
}
