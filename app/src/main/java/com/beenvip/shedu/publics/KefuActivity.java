package com.beenvip.shedu.publics;

import android.os.Bundle;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;

/**
 * Created by ZH on 2017/4/10.
 * 497239511@qq.com
 */

public class KefuActivity extends BaseActivity {
    @Override
    protected void initData() {
        setTitle("联系客服");
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
        setContentView(R.layout.activity_kefu);

    }
}
