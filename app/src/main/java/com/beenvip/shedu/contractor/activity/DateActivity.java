package com.beenvip.shedu.contractor.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.beenvip.shedu.R;
import com.beenvip.shedu.base.BaseActivity;
import com.bigkoo.pickerview.OptionsPickerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZH on 2017/4/7.
 * 497239511@qq.com
 */

public class DateActivity extends BaseActivity {
    private Button btn;
    OptionsPickerView pickerView;
    private List<String> datas;
    @Override
    protected void initData() {
        datas=new ArrayList<>();
        datas.add("全部");
        datas.add("一天内");
        datas.add("三天内");
        datas.add("一周内");
        datas.add("一月内");
        datas.add("半年内");

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
        setContentView(R.layout.date);
        btn=findView(R.id.btn);
          pickerView=new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                showToastMsg(datas.get(options1));

            }
        }).build();
        pickerView.setPicker(datas);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickerView.show();
            }
        });

    }
}
