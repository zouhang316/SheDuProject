package com.beenvip.shedu.fabaofang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.publics.bean.FenleiBean;
import com.bigkoo.pickerview.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ZH on 2017/4/21.
 * 497239511@qq.com
 */

public class AddProjectActivity extends BaseActivity implements View.OnClickListener {


    private RelativeLayout mProjectClass;
    private FenleiBean fenleiBeanData;
    private RelativeLayout mSelectTime;
    private TextView mLastTime;

    @Override
    protected void initData() {
        getData();
    }

    @Override
    protected void initListener() {
        mProjectClass.setOnClickListener(this);
        mSelectTime.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addproject);
        setTitle("增加项目");
        mProjectClass=findView(R.id.projectclass);
        mSelectTime=findView(R.id.selectlasttime);
        mLastTime=findView(R.id.lasttime);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.projectclass:
               startActivity(new Intent(AddProjectActivity.this,MoreActivity.class).putExtra("title","工程分类").putExtra("data",fenleiBeanData));
                break;
            case R.id.selectlasttime:
                showTimePickView();
                break;
        }
    }
    private void getData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("type","1");
        httpHelper.asyncGetRequest(ApiContacts.INDEX_GETSORT, paramers, FenleiBean.class, new HttpCallBackListener<FenleiBean>() {

            @Override
            public void onSuccess(FenleiBean fenleiBean) {
                fenleiBeanData=fenleiBean;
            }

            @Override
            public void onFailed(FenleiBean fenleiBean) {

            }
        },false);
    }
    private void showTimePickView(){
        Calendar selectdate=Calendar.getInstance();
        Calendar star=Calendar.getInstance();
        star.set(2016,0,1);
        Calendar end=Calendar.getInstance();
        end.set(2056,0,1);
        TimePickerView pickerView=new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                mLastTime.setText(getTime(date));
            }
        }).setDate(selectdate)
                .setRangDate(star,end)
                .setLabel("","","","","","")
                .setType(TimePickerView.Type.YEAR_MONTH_DAY).build();
        pickerView.show();
    }
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

}
