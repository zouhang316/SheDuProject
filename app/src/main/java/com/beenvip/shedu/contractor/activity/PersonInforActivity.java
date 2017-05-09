package com.beenvip.shedu.contractor.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.fabaofang.activity.MoreActivity;
import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.publics.bean.FenleiBean;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ZH on 2017/4/7.
 * 497239511@qq.com
 */

public class PersonInforActivity extends BaseActivity implements View.OnClickListener{
    private RelativeLayout sex;
    private RelativeLayout workYear;
    private RelativeLayout education;
    private RelativeLayout gongZhong;
    private OptionsPickerView pickerView;
    private List<String> sexData;
    private List<String> educationData;
    private List<String> workYearData;
    private List<String> shenData;
    private TextView sex_tv;
    private TextView workeYear_tv;
    private TextView education_tv;
    private TextView gongzhong_tv;
    private FenleiBean fenleiBeanData;
    @Override
    protected void initData() {
        sexData=new ArrayList<>();
        sexData.add("男");
        sexData.add("女");

        educationData=new ArrayList<>();
        educationData.add("小学");
        educationData.add("初中");
        educationData.add("中专");
        educationData.add("高中");
        educationData.add("大专");
        educationData.add("本科");
        educationData.add("硕士");
        educationData.add("博士");
        educationData.add("博士后");

        workYearData=new ArrayList<>();
        workYearData.add("一年");
        workYearData.add("两年");
        workYearData.add("三年");
        workYearData.add("四年");
        workYearData.add("五年");
        workYearData.add("六年");
        workYearData.add("七年");
        workYearData.add("八年");
        workYearData.add("九年");
        workYearData.add("十年或以上");
        getGzhongData();

    }

    @Override
    protected void initListener() {
        sex.setOnClickListener(this);
        workYear.setOnClickListener(this);
        education.setOnClickListener(this);
        gongZhong.setOnClickListener(this);

    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personinfo);
        setTitle("个人资料");
        sex=findView(R.id.sex);
        workYear=findView(R.id.workyear);
        education=findView(R.id.education);
        gongZhong=findView(R.id.gongzhong);
        sex_tv=findView(R.id.sex_tv);
        workeYear_tv=findView(R.id.year_tv);
        education_tv=findView(R.id.education_tv);
        gongzhong_tv=findView(R.id.gongzhong_tv);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.sex:
                showPickView(sexData,sex_tv);
                break;
            case R.id.workyear:
                showPickView(workYearData,workeYear_tv);
                break;
            case R.id.education:
                showPickView(educationData,education_tv);
                break;
            case R.id.gongzhong:
                Intent intent=new Intent(PersonInforActivity.this,MoreActivity.class);
                intent.putExtra("data",fenleiBeanData);
                intent.putExtra("title","工种分类");
                startActivityForResult(intent,1);
                break;
        }
    }
    private void showPickView(final List<String> datas, final TextView textView){
        pickerView=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                textView.setText(datas.get(options1));
            }
        }).build();
        pickerView.setPicker(datas);
        pickerView.show();
    }
    private void getGzhongData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("type","3");
        httpHelper.asyncGetRequest(ApiContacts.INDEX_GETSORT, paramers, FenleiBean.class, new HttpCallBackListener<FenleiBean>() {
            @Override
            public void onSuccess(FenleiBean fenleiBean) {
                fenleiBeanData=fenleiBean;
            }

            @Override
            public void onFailed(FenleiBean fenleiBean) {
            }
        },true);
    }
    private void getShenData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("mid","5");
//        httpHelper.asyncGetRequest(ApiContacts.LIST, paramers, CityBean.class, new HttpCallBackListener<CityBean>() {
//            @Override
//            public void onSuccess(CityBean cityBean) {
//
//            }
//
//            @Override
//            public void onFailed(CityBean cityBean) {
//
//            }
//
//        },false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode== Activity.RESULT_OK){
            gongzhong_tv.setText(data.getStringExtra("data"));
        }
    }
}
