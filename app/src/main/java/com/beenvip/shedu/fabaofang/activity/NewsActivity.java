package com.beenvip.shedu.fabaofang.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;

/**
 * Created by ZH on 2017/5/5.
 * 497239511@qq.com
 */

public class NewsActivity extends BaseActivity {
    private String title;
    private ListView listView;
    @Override
    protected void initData() {
        title=getIntent().getStringExtra("title");
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
        setContentView(R.layout.activity_news);
        setTitle(title);
        listView=findView(R.id.listview);


    }
}
