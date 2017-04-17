package com.beenvip.shedu.fabaofang.activity;

import android.os.Bundle;
import android.widget.ListView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.fabaofang.adapter.MoreAdapter;
import com.beenvip.shedu.user.bean.FenleiBean;

import java.util.List;

/**
 * Created by ZH on 2017/4/17.
 * 497239511@qq.com
 */

public class MoreActivity extends BaseActivity {
    private ListView listView;
    private MoreAdapter adapter;
    private List<FenleiBean.DataBean> beanList;
    @Override
    protected void initData() {
        beanList=getIntent().getParcelableArrayListExtra("data");

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
        setContentView(R.layout.activity_fabao_index_more);
        setTitle("班组分类");
        listView=findView(R.id.morelistview);
        adapter=new MoreAdapter(this,beanList);
        listView.setAdapter(adapter);
    }
}
