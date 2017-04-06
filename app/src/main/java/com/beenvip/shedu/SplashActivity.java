package com.beenvip.shedu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.beenvip.shedu.base.BaseActivity;

/**
 * Created by ZH on 2017/3/24.
 * 497239511@qq.com
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void initData() {


    }

    @Override
    protected void initListener() {

    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setFitsSystemWindows(false);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 3000);
    }
}
