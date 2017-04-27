package com.beenvip.shedu.fabaofang.activity;

import android.os.Bundle;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.fabaofang.bean.ProjectBean;
import com.beenvip.shedu.http.HttpListener;
import com.beenvip.shedu.utils.LalaLog;

import java.util.HashMap;

/**
 * Created by ZH on 2017/4/27.
 * 497239511@qq.com
 */

public class SelectProject extends BaseActivity {
    @Override
    protected void initData() {
        getData();
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
        setContentView(R.layout.activity_selectproject);
        setTitle("选择项目");
    }
    private void getData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mid","5");
        paramers.put("which"," AND uid=3");
        httpHelper.asyncGetRequest(ApiContacts.LIST, paramers, ProjectBean.class, new HttpListener<ProjectBean>() {
            @Override
            public void onSuccess(ProjectBean projectBean) {
                   LalaLog.d("zzz",projectBean.toString());
            }

            @Override
            public void onFailed(ProjectBean projectBean) {

            }

        },false);
    }
}
