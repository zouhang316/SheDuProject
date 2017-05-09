package com.beenvip.shedu.fabaofang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.fabaofang.adapter.ProjectAdapter;
import com.beenvip.shedu.fabaofang.bean.ProjectBean;
import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * Created by ZH on 2017/4/27.
 * 497239511@qq.com
 */

public class SelectProject extends BaseActivity {
    private ListView listView;
    private ProjectAdapter adapter;
    private ProjectBean mProjectBean;
    private int action;
    public static final int ZHAOBANZU=0;
    public static final int ZHAOGONGREN=1;
    @Override
    protected void initData() {
        getData();
        action=getIntent().getIntExtra("action",0);
    }

    @Override
    protected void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (action){
                    case ZHAOBANZU:
                        Intent zhaoBanzu=new Intent(SelectProject.this,ZhaoMuBanzuActvity.class);
                        zhaoBanzu.putExtra("pid",mProjectBean.getData().get(position).getPid());
                        startActivity(zhaoBanzu);
                        break;
                    case ZHAOGONGREN:
                        Intent zhaoGongRen=new Intent(SelectProject.this,ZhaoMuGongrenActivity.class);
                        zhaoGongRen.putExtra("pid",mProjectBean.getData().get(position).getPid());
                        startActivity(zhaoGongRen);
                        break;
                }
            }
        });
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_selectproject);
        setTitle("选择项目");
        listView=findView(R.id.listview);
    }
    private void getData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mid","5");
        paramers.put("which"," AND uid=3");
        httpHelper.asyncGetRequest(ApiContacts.LIST, paramers, ProjectBean.class, new HttpCallBackListener<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean) {
                LalaLog.d("zzz",projectBean.toString());
                mProjectBean=projectBean;
                adapter=new ProjectAdapter(SelectProject.this,projectBean.getData());
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailed(ProjectBean projectBean) {

            }


        },true);
    }
}
