package com.beenvip.shedu.contractor.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.beenvip.shedu.contractor.adapter.AddressSwitchAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/6.
 * 497239511@qq.com
 */

public class AddressSwicthActivity extends BaseActivity implements View.OnClickListener{
    private ListView cityLv;
    private List<String> provinceList;
    private List<String> cityList;
    private List<String> cityList1;
    private List<String> cityList2;
    private AddressSwitchAdapter adapter;
    private RadioGroup radioGroup;
    @Override
    protected void initData() {
        provinceList=new ArrayList<>();
        provinceList.add("不限");
        provinceList.add("湖南省");
        provinceList.add("湖北省");
        provinceList.add("安徽省");
        provinceList.add("河南省");
        provinceList.add("江西省");
        provinceList.add("浙江省");
        provinceList.add("江苏省");
        provinceList.add("黑龙江省");
        cityList1=new ArrayList<>();
        cityList1.add("不限");
        cityList1.add("长沙市");
        cityList1.add("株洲市");
        cityList1.add("湘潭市");
        cityList1.add("益阳市");
        cityList1.add("娄底市");
        cityList1.add("郴州市");
        cityList1.add("岳阳市");
        cityList1.add("常德市");
        cityList2=new ArrayList<>();
        cityList2.add("不限");
        cityList2.add("武汉市");
        cityList2.add("黄石市");
        cityList2.add("宜昌市");
        cityList2.add("仙桃市");
        cityList2.add("襄阳市");
        cityList2.add("孝感市");
        cityList2.add("黄冈市");
        cityList=new ArrayList<>();
        cityList.addAll(cityList1);



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
        setContentView(R.layout.activity_addressswich);
        setTitle("工作地点");
        cityLv=findView(R.id.city_lv);
        adapter=new AddressSwitchAdapter(this,cityList);
        cityLv.setAdapter(adapter);
        radioGroup=findView(R.id.radiogroup);
        for (int i = 0; i < provinceList.size(); i++) {
            RadioGroup.LayoutParams params=new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            RadioButton button= (RadioButton) LayoutInflater.from(this).inflate(R.layout.radiobutton,null);
            button.setLayoutParams(params);
            button.setText(provinceList.get(i));
            button.setId(i);
            button.setOnClickListener(this);
            radioGroup.addView(button);
        }
        cityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToastMsg(cityList.get(position));
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case 1:
                cityList.clear();
                cityList.addAll(cityList1);
                adapter.notifyDataSetChanged();
                break;
            case 2:
                cityList.clear();
                cityList.addAll(cityList2);
                adapter.notifyDataSetChanged();
                break;
        }

    }
}
