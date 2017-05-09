package com.beenvip.shedu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.alibaba.fastjson.JSONObject;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.event.CitySelectBean;
import com.beenvip.shedu.publics.activity.FirstSelectIdentityActivity;
import com.beenvip.shedu.publics.bean.NewCityBean;
import com.beenvip.shedu.utils.CommUtils;
import com.beenvip.shedu.utils.GetJsonDataUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/3/24.
 * 497239511@qq.com
 */

public class SplashActivity extends BaseActivity {
    private CitySelectBean bean;
    @Override
    protected void initData() {
        //检查网络
        if (CommUtils.isNetworkConnected(this)){
            //showToastMsg("OK");
        }else {
            //showToastMsg("NO");
        }
        getCity();

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
        EventBus.getDefault().postSticky(bean);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this,FirstSelectIdentityActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 500);
    }
    //获取帅选器城市数据 用Event Bus 发送
    private void getCity(){
        bean=new CitySelectBean();
        String jsonData= new GetJsonDataUtil().getJson(this,"city.json");
        List<List<String>> arrayLists=new ArrayList<>();
        List<String> cityList=new ArrayList<>();
        List<NewCityBean> beanList= JSONObject.parseArray(jsonData,NewCityBean.class);

        for (int i = 0; i < beanList.size(); i++) {
            ArrayList<String> strings=new ArrayList<>();
            cityList.add(beanList.get(i).getName());
            for (int j = 0; j <beanList.get(i).getCity().size() ; j++) {
                strings.add(beanList.get(i).getCity().get(j).getName());
            }
            arrayLists.add(strings);
        }
        bean.setProvinceList(cityList);
        bean.setCity(arrayLists);
    }
}
