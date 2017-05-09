package com.beenvip.shedu.fabaofang.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.beenvip.shedu.R;
import com.beenvip.shedu.api.ApiContacts;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.event.CitySelectBean;
import com.beenvip.shedu.http.httpurlconnection.HttpCallBackListener;
import com.beenvip.shedu.publics.bean.FenleiBean;
import com.beenvip.shedu.publics.bean.NewCityBean;
import com.beenvip.shedu.utils.GetJsonDataUtil;
import com.beenvip.shedu.utils.LalaLog;
import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * Created by ZH on 2017/4/28.
 * 497239511@qq.com
 */

public class ZhaoMuBanzuActvity extends BaseActivity implements View.OnClickListener {
    private RelativeLayout banzuClass;
    private TextView banzuName;
    private FenleiBean mFenleiBean;
    private RelativeLayout selectStartTime;
    private RelativeLayout selectLastTime;
    private RelativeLayout payType;
    private TextView payType_tv;
    private TextView startTime;
    private TextView lastTime;
    private List<String> payTypeList;
    private List<NewCityBean> beanList;
    private RelativeLayout selectCity;
    private List<ArrayList<String>> arrayLists;
    private List<String> cityList;
    private CitySelectBean citySelectBean;

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        LalaLog.d(getIntent().getStringExtra("pid"));
        getBanzuData();
        setMyData();
        //getCity();
    }

    @Override
    protected void initListener() {
        banzuClass.setOnClickListener(this);
        selectStartTime.setOnClickListener(this);
        selectLastTime.setOnClickListener(this);
        payType.setOnClickListener(this);
        selectCity.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_zhaomubanzu);
        setTitle("招募班组");
        banzuClass=findView(R.id.banzuclass);
        banzuName=findView(R.id.banzuname);
        selectStartTime=findView(R.id.selectstarttime);
        selectLastTime=findView(R.id.selectlasttime);
        startTime=findView(R.id.startime);
        lastTime=findView(R.id.lasttime);
        payType=findView(R.id.paytype);
        payType_tv=findView(R.id.paytype_tv);
        selectCity=findView(R.id.selectcity);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.banzuclass:
                Intent intent=new Intent(ZhaoMuBanzuActvity.this,MoreActivity.class);
                intent.putExtra("data",mFenleiBean);
                intent.putExtra("title","班组分类");
                startActivityForResult(intent,1);
                break;
            case R.id.selectstarttime:
                showTimePickView(1);
                break;
            case R.id.selectlasttime:
                showTimePickView(2);
                break;
            case R.id.paytype:
                showPayPickView();
                break;
            case R.id.selectcity:
                ShowPickerView();
                break;

        }
    }

    private void getBanzuData(){
        HashMap<String,String> paramers=new HashMap<>();
        paramers.put("type","2");
        httpHelper.asyncGetRequest(ApiContacts.INDEX_GETSORT, paramers, FenleiBean.class, new HttpCallBackListener<FenleiBean>() {
            @Override
            public void onSuccess(FenleiBean fenleiBean) {
                mFenleiBean=fenleiBean;
            }

            @Override
            public void onFailed(FenleiBean fenleiBean) {
            }

        },false);
    }

    private void showTimePickView(final int type){
        Calendar selectdate=Calendar.getInstance();
        Calendar star=Calendar.getInstance();
        star.set(2016,0,1);
        Calendar end=Calendar.getInstance();
        end.set(2056,0,1);
        TimePickerView pickerView=new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                if (type==1){
                    startTime.setText(getTime(date));
                }else {
                    lastTime.setText(getTime(date));
                }

            }
        }).setDate(selectdate)
                .setRangDate(star,end)
                .setLabel("年","月","日","","","")
                .setType(TimePickerView.Type.YEAR_MONTH_DAY).build();
        pickerView.show();
    }

    private void setMyData(){
        payTypeList=new ArrayList<>();
        payTypeList.add("工程进度比例结");
        payTypeList.add("月进度结");
        payTypeList.add("完工结清");
        payTypeList.add("面议");
    }

    private void showPayPickView(){
       OptionsPickerView pickerView=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                payType_tv.setText(payTypeList.get(options1));
            }
        }).build();
        pickerView.setPicker(payTypeList);
        pickerView.show();
    }
    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView  pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
//                String tx = options1Items.get(options1).getPickerViewText()+
//                        options2Items.get(options1).get(options2)+
//                        options3Items.get(options1).get(options2).get(options3);
//
//                Toast.makeText(JsonDataActivity.this,tx,Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(citySelectBean.getProvinceList(),citySelectBean.getCity());//三级选择器  不能够转换为String
        pvOptions.show();
    }

    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    private void getCity(){
        String jsonData= new GetJsonDataUtil().getJson(this,"city.json");
        arrayLists=new ArrayList<>();
        cityList=new ArrayList<>();
        beanList=JSONObject.parseArray(jsonData,NewCityBean.class);
        for (int i = 0; i < beanList.size(); i++) {
            ArrayList<String> strings=new ArrayList<>();
            cityList.add(beanList.get(i).getName());
            for (int j = 0; j <beanList.get(i).getCity().size() ; j++) {
                    strings.add(beanList.get(i).getCity().get(j).getName());
            }
            arrayLists.add(strings);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode== Activity.RESULT_OK){
            banzuName.setText(data.getStringExtra("data"));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getCitySelectData(CitySelectBean bean){
        citySelectBean=bean;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
